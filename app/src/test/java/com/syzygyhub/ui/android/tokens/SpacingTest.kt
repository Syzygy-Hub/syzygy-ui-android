package com.syzygyhub.ui.android.tokens

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SpacingTest {
    @Test
    fun spacing_scale_is_ordered_ascending() {
        val scale = listOf(Spacing.xs, Spacing.sm, Spacing.md, Spacing.lg, Spacing.xl, Spacing.xxl)
        for (i in 0 until scale.size - 1) {
            assertTrue(scale[i].value < scale[i + 1].value)
        }
    }

    @Test
    fun spacing_md_is_16dp() {
        assertEquals(16f, Spacing.md.value)
    }
}
