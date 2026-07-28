package com.aks.android_ui_library.components.badges

import org.junit.Assert.assertEquals
import org.junit.Test

class BadgeVariantTest {

    @Test
    fun all_expected_variants_exist() {
        val names = BadgeVariant.entries.map { it.name }.toSet()
        assertEquals(setOf("PRIMARY", "SUCCESS", "WARNING", "ERROR"), names)
    }
}
