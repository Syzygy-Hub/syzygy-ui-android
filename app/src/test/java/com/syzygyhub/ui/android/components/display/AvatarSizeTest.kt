package com.syzygyhub.ui.android.components.display

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AvatarSizeTest {
    @Test
    fun all_expected_sizes_exist() {
        val names = AvatarSize.entries.map { it.name }.toSet()
        assertEquals(setOf("SMALL", "MEDIUM", "LARGE"), names)
    }

    @Test
    fun sizes_are_ordered_ascending() {
        val scale = listOf(AvatarSize.SMALL.dimension, AvatarSize.MEDIUM.dimension, AvatarSize.LARGE.dimension)
        for (i in 0 until scale.size - 1) {
            assertTrue(scale[i].value < scale[i + 1].value)
        }
    }
}
