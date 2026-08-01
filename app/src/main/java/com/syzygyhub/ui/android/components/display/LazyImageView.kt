package com.syzygyhub.ui.android.components.display

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.collection.LruCache
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.components.feedback.ShimmerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.net.URL
import java.security.MessageDigest

/**
 * A tiny, dependency-free two-tier bitmap cache: an in-memory LRU (fast,
 * cleared on process death) backed by an on-disk cache in [Context.cacheDir]
 * (survives process death; reclaimed by the OS under storage pressure like
 * any other cache-dir content).
 *
 * This deliberately does NOT implement HTTP cache-header revalidation
 * (ETag / Cache-Control / max-age) — that's the point where a real
 * dependency (Coil/Glide) earns its keep. Given this library's "zero
 * third-party dependencies" constraint, a hash-keyed cache with no
 * revalidation is the pragmatic stopping point: it eliminates redundant
 * fetches for the same URL within a session and across process restarts —
 * the dominant cost in a scrolling list — without reimplementing HTTP
 * caching semantics from scratch.
 */
internal object ImageCache {
    // Classic "1/8th of available app memory" sizing, the same fraction
    // Coil/Glide/Picasso default to — enough to hold a screen's worth of
    // thumbnails without risking OOM on low-memory devices.
    private val maxMemoryKb = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val memoryCache =
        object : LruCache<String, Bitmap>(maxMemoryKb / 8) {
            override fun sizeOf(
                key: String,
                value: Bitmap
            ): Int = value.byteCount / 1024
        }

    fun getMemory(key: String): Bitmap? = memoryCache.get(key)

    fun putMemory(
        key: String,
        bitmap: Bitmap
    ) {
        memoryCache.put(key, bitmap)
    }

    fun diskCacheDir(context: Context): File = File(context.cacheDir, "syzygy_image_cache").apply { mkdirs() }

    fun diskFile(
        context: Context,
        key: String
    ): File = File(diskCacheDir(context), key)

    /** SHA-256 of [url] so cache filenames are always filesystem-safe. */
    fun hashUrl(url: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(url.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}

/**
 * Disk cache size cap: no eviction policy meant the cache directory grew by
 * one file per unique URL ever loaded, forever. 75MB is a generous but
 * bounded budget for a thumbnail/image cache — comparable to what Coil/Glide
 * default to for a mid-size app.
 */
internal const val MAX_DISK_CACHE_BYTES = 75L * 1024 * 1024

/**
 * Deletes files in [dir], oldest-[File.lastModified] first, until the
 * directory's total size is at or under [maxBytes]. Kept as a standalone,
 * pure-file-I/O function (no [Context] dependency) so it's directly unit
 * testable against a temp directory.
 *
 * Ordering by `lastModified` approximates least-recently-*used* rather than
 * least-recently-*written*, because [loadBitmap] touches a file's
 * modification time on every disk-cache hit (see call site below) — so a
 * frequently re-displayed image survives eviction even if it was originally
 * written long ago.
 */
internal fun evictLeastRecentlyUsed(
    dir: File,
    maxBytes: Long,
) {
    val files = dir.listFiles()?.toList() ?: return
    var totalBytes = files.sumOf { it.length() }
    if (totalBytes <= maxBytes) return

    for (file in files.sortedBy { it.lastModified() }) {
        if (totalBytes <= maxBytes) break
        val size = file.length()
        if (file.delete()) {
            totalBytes -= size
        }
    }
}

private sealed interface ImageLoadState {
    data object Loading : ImageLoadState

    data class Success(val bitmap: Bitmap) : ImageLoadState

    data object Failure : ImageLoadState
}

internal const val MAX_ATTEMPTS = 2
internal const val RETRY_BACKOFF_MS = 500L

/**
 * Runs [block] up to [attempts] times, delaying [backoffMs] between
 * retries, returning the first successful result or null if every attempt
 * throws an [IOException]. Extracted as a standalone function (rather than
 * inlined into [loadBitmap]) so the retry/backoff behavior itself is unit
 * testable without needing a real network call or [Bitmap].
 */
internal suspend fun <T> retryOnIOException(
    attempts: Int,
    backoffMs: Long,
    block: suspend () -> T,
): T? {
    repeat(attempts) { attempt ->
        try {
            return block()
        } catch (e: IOException) {
            if (attempt < attempts - 1) delay(backoffMs)
        }
    }
    return null
}

/**
 * Resolves [url] to a [Bitmap] via memory cache -> disk cache -> network
 * (with one retry on transient failure), populating both cache tiers on a
 * successful network fetch. Returns null if every attempt fails.
 */
private suspend fun loadBitmap(
    context: Context,
    url: String
): Bitmap? {
    val key = ImageCache.hashUrl(url)

    ImageCache.getMemory(key)?.let { return it }

    val diskHit =
        withContext(Dispatchers.IO) {
            val file = ImageCache.diskFile(context, key)
            if (file.exists()) {
                // Touch on read so eviction order reflects last-*used*, not
                // just last-written — a frequently redisplayed image should
                // outlive one loaded once and never shown again.
                file.setLastModified(System.currentTimeMillis())
                BitmapFactory.decodeFile(file.absolutePath)
            } else {
                null
            }
        }
    if (diskHit != null) {
        ImageCache.putMemory(key, diskHit)
        return diskHit
    }

    return retryOnIOException(MAX_ATTEMPTS, RETRY_BACKOFF_MS) {
        val bytes =
            withContext(Dispatchers.IO) {
                URL(url).openStream().use { it.readBytes() }
            }
        val bitmap =
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                ?: throw IOException("Failed to decode bitmap from $url")
        ImageCache.putMemory(key, bitmap)
        withContext(Dispatchers.IO) {
            try {
                ImageCache.diskFile(context, key).writeBytes(bytes)
                evictLeastRecentlyUsed(ImageCache.diskCacheDir(context), MAX_DISK_CACHE_BYTES)
            } catch (e: IOException) {
                // Best-effort: a disk-cache write failure (e.g. storage
                // full) shouldn't fail the load — the bitmap is already
                // decoded and memory-cached.
            }
        }
        bitmap
    }
}

/**
 * Asynchronously loads a remote image with a two-tier (memory + disk) cache
 * and one retry on transient failure — no third-party image-loading
 * dependency. Shows a [ShimmerView] placeholder while loading and a
 * fallback glyph if the load ultimately fails.
 *
 * Cancellation: the fetch runs inside [LaunchedEffect] keyed on [url].
 * Compose cancels a `LaunchedEffect`'s coroutine whenever its key changes or
 * the composable leaves composition — which includes a row scrolling out of
 * a `LazyColumn`'s viewport, since Compose disposes that row's composition
 * entirely. No manual cancellation bookkeeping is needed; keying on `url` is
 * what makes scroll-away cancellation automatic.
 */
@Composable
fun LazyImageView(
    url: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    var state by remember(url) { mutableStateOf<ImageLoadState>(ImageLoadState.Loading) }

    LaunchedEffect(url) {
        if (url.isNullOrBlank()) {
            state = ImageLoadState.Failure
            return@LaunchedEffect
        }
        val bitmap = loadBitmap(context, url)
        state = if (bitmap != null) ImageLoadState.Success(bitmap) else ImageLoadState.Failure
    }

    when (val current = state) {
        is ImageLoadState.Loading -> ShimmerView(modifier = modifier)
        is ImageLoadState.Success ->
            Image(
                bitmap = current.bitmap.asImageBitmap(),
                contentDescription = "Image",
                modifier = modifier,
                contentScale = contentScale,
            )
        is ImageLoadState.Failure ->
            Box(
                modifier =
                    modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .semantics { contentDescription = "Image failed to load" },
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = Icons.Filled.BrokenImage, contentDescription = null)
            }
    }
}
