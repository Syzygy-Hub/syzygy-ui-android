package com.syzygyhub.ui.android.components.display

import android.graphics.Bitmap
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class LazyImageViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shows_failure_fallback_for_null_url() {
        composeTestRule.setContent {
            LazyImageView(url = null)
        }

        composeTestRule.onNodeWithContentDescription("Image failed to load").assertExists()
    }

    @Test
    fun shows_failure_fallback_for_blank_url() {
        composeTestRule.setContent {
            LazyImageView(url = "   ")
        }

        composeTestRule.onNodeWithContentDescription("Image failed to load").assertExists()
    }

    @Test
    fun memory_cache_round_trips_a_bitmap_by_hashed_key() {
        val key = ImageCache.hashUrl("https://example.com/round-trip.jpg")
        val bitmap = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888)

        ImageCache.putMemory(key, bitmap)

        assert(ImageCache.getMemory(key) === bitmap)
    }

    @Test
    fun disk_cache_file_lives_under_the_app_cache_dir_named_by_hash() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val key = ImageCache.hashUrl("https://example.com/disk-cache.jpg")

        val file = ImageCache.diskFile(context, key)

        assert(file.name == key)
        assert(file.parentFile?.parentFile?.absolutePath == context.cacheDir.absolutePath)
    }
}
