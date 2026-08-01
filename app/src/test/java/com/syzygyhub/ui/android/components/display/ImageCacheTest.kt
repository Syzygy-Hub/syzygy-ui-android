package com.syzygyhub.ui.android.components.display

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.io.IOException

class ImageCacheTest {
    @Test
    fun hashUrl_is_deterministic_for_the_same_url() {
        val url = "https://example.com/photo.jpg"
        assertEquals(ImageCache.hashUrl(url), ImageCache.hashUrl(url))
    }

    @Test
    fun hashUrl_differs_for_different_urls() {
        val a = ImageCache.hashUrl("https://example.com/a.jpg")
        val b = ImageCache.hashUrl("https://example.com/b.jpg")
        assertNotEquals(a, b)
    }

    @Test
    fun hashUrl_is_filesystem_safe() {
        val hash = ImageCache.hashUrl("https://example.com/a b?c=d#e.jpg")
        // SHA-256 hex digest: 64 lowercase hex characters, no path separators
        // or query-string characters that would break a cache filename.
        assertEquals(64, hash.length)
        assertEquals(hash, hash.filter { it.isDigit() || it in 'a'..'f' })
    }
}

class EvictLeastRecentlyUsedTest {
    private fun tempCacheDir(): File {
        val dir = File.createTempFile("image_cache_test", null)
        dir.delete()
        dir.mkdirs()
        return dir
    }

    private fun fileOfSize(
        dir: File,
        name: String,
        bytes: Int,
        lastModified: Long,
    ): File =
        File(dir, name).apply {
            writeBytes(ByteArray(bytes))
            setLastModified(lastModified)
        }

    @Test
    fun does_nothing_when_total_size_is_under_the_cap() {
        val dir = tempCacheDir()
        fileOfSize(dir, "a", bytes = 100, lastModified = 1_000)
        fileOfSize(dir, "b", bytes = 100, lastModified = 2_000)

        evictLeastRecentlyUsed(dir, maxBytes = 1_000)

        assertEquals(2, dir.listFiles()?.size)
    }

    @Test
    fun evicts_oldest_files_first_until_under_the_cap() {
        val dir = tempCacheDir()
        // Three 100-byte files; a 250-byte cap can only keep two of them, so
        // the single oldest (by lastModified) must be evicted.
        fileOfSize(dir, "oldest", bytes = 100, lastModified = 1_000)
        fileOfSize(dir, "middle", bytes = 100, lastModified = 2_000)
        fileOfSize(dir, "newest", bytes = 100, lastModified = 3_000)

        evictLeastRecentlyUsed(dir, maxBytes = 250)

        val remaining = dir.listFiles()?.map { it.name }.orEmpty()
        assertFalse("oldest file should have been evicted", remaining.contains("oldest"))
        assertTrue(remaining.contains("middle"))
        assertTrue(remaining.contains("newest"))
    }

    @Test
    fun stops_evicting_as_soon_as_the_total_size_is_at_or_under_the_cap() {
        val dir = tempCacheDir()
        fileOfSize(dir, "oldest", bytes = 100, lastModified = 1_000)
        fileOfSize(dir, "newer", bytes = 100, lastModified = 2_000)

        // Cap comfortably fits both files after evicting nothing; the
        // "middle" ordering above already proves partial eviction, this
        // proves a cap that fits everything evicts nothing.
        evictLeastRecentlyUsed(dir, maxBytes = 200)

        assertEquals(2, dir.listFiles()?.size)
    }

    @Test
    fun evicts_multiple_files_if_needed_to_get_under_the_cap() {
        val dir = tempCacheDir()
        fileOfSize(dir, "a", bytes = 100, lastModified = 1_000)
        fileOfSize(dir, "b", bytes = 100, lastModified = 2_000)
        fileOfSize(dir, "c", bytes = 100, lastModified = 3_000)
        fileOfSize(dir, "d", bytes = 100, lastModified = 4_000)

        evictLeastRecentlyUsed(dir, maxBytes = 150)

        val remaining = dir.listFiles()?.map { it.name }.orEmpty()
        // Only the single newest 100-byte file fits under a 150-byte cap.
        assertEquals(listOf("d"), remaining)
    }
}

class RetryOnIOExceptionTest {
    @Test
    fun returns_result_on_first_success_without_retrying() =
        runTest {
            var calls = 0
            val result =
                retryOnIOException(attempts = MAX_ATTEMPTS, backoffMs = 0) {
                    calls++
                    "ok"
                }
            assertEquals("ok", result)
            assertEquals(1, calls)
        }

    @Test
    fun retries_once_after_a_single_failure_then_succeeds() =
        runTest {
            var calls = 0
            val result =
                retryOnIOException(attempts = MAX_ATTEMPTS, backoffMs = 0) {
                    calls++
                    if (calls == 1) throw IOException("transient")
                    "ok"
                }
            assertEquals("ok", result)
            assertEquals(2, calls)
        }

    @Test
    fun returns_null_after_exhausting_all_attempts() =
        runTest {
            var calls = 0
            val result =
                retryOnIOException(attempts = MAX_ATTEMPTS, backoffMs = 0) {
                    calls++
                    throw IOException("still failing")
                }
            assertNull(result)
            assertEquals(MAX_ATTEMPTS, calls)
        }

    @Test
    fun does_not_retry_beyond_the_configured_attempt_count() =
        runTest {
            var calls = 0
            retryOnIOException(attempts = 3, backoffMs = 0) {
                calls++
                throw IOException("always fails")
            }
            assertEquals(3, calls)
        }
}
