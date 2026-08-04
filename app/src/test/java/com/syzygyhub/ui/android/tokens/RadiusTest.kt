package com.syzygyhub.ui.android.tokens

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RadiusTest {
    @Test
    fun radius_scale_is_ordered_ascending() {
        val scale = listOf(Radius.sm, Radius.md, Radius.lg, Radius.full)
        for (i in 0 until scale.size - 1) {
            assertTrue(scale[i].value < scale[i + 1].value)
        }
    }

    @Test
    fun radius_full_is_effectively_a_pill() {
        assertEquals(9999f, Radius.full.value)
    }
}
