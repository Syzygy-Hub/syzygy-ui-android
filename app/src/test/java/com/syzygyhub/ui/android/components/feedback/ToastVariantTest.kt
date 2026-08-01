package com.syzygyhub.ui.android.components.feedback

import org.junit.Assert.assertEquals
import org.junit.Test

class ToastVariantTest {
    @Test
    fun all_expected_variants_exist() {
        val names = ToastVariant.entries.map { it.name }.toSet()
        assertEquals(setOf("SUCCESS", "WARNING", "ERROR"), names)
    }
}
