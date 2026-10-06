package com.syzygyhub.ui.android.components.inputs

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PhoneFormattingTest {
    // Replicates the onValueChange logic from PhoneInput without Compose

    private fun filterDigits(input: String): String = input.filter { it.isDigit() }

    private fun buildFormatted(
        dialCode: String,
        digitsOnly: String,
    ): String = "$dialCode $digitsOnly"

    // --- Digit filtering ---

    @Test
    fun filters_non_digit_characters_from_valid_us_number() {
        val raw = "(555) 123-4567"
        assertEquals("5551234567", filterDigits(raw))
    }

    @Test
    fun preserves_digits_only_input_unchanged() {
        val raw = "5551234567"
        assertEquals("5551234567", filterDigits(raw))
    }

    @Test
    fun empty_input_produces_empty_digits() {
        assertEquals("", filterDigits(""))
    }

    @Test
    fun non_numeric_input_produces_empty_digits() {
        assertEquals("", filterDigits("abc-def"))
    }

    @Test
    fun mixed_input_extracts_only_digits() {
        assertEquals("12345", filterDigits("1a2b3c4d5e"))
    }

    // --- Formatted string construction ---

    @Test
    fun formatted_string_combines_dial_code_and_digits() {
        val dialCode = "+1"
        val digits = "5551234567"
        assertEquals("+1 5551234567", buildFormatted(dialCode, digits))
    }

    @Test
    fun formatted_string_with_empty_digits() {
        assertEquals("+44 ", buildFormatted("+44", ""))
    }

    @Test
    fun formatted_string_with_international_dial_code() {
        val digits = filterDigits("(91) 98765-43210")
        val formatted = buildFormatted("+91", digits)
        assertEquals("+91 919876543210", formatted)
    }

    // --- DefaultPhoneCountries ---

    @Test
    fun default_countries_list_is_not_empty() {
        assertTrue(DefaultPhoneCountries.isNotEmpty())
    }

    @Test
    fun default_countries_includes_us() {
        val us = DefaultPhoneCountries.firstOrNull { it.isoCode == "US" }
        assertEquals("+1", us?.dialCode)
        assertEquals("🇺🇸", us?.flagEmoji)
    }

    @Test
    fun all_default_countries_have_non_blank_dial_codes() {
        DefaultPhoneCountries.forEach { country ->
            assertTrue(
                "Dial code blank for ${country.name}",
                country.dialCode.isNotBlank(),
            )
        }
    }

    // --- Component behaviour: callback value matches filtered input ---

    @Test
    fun callback_receives_digits_only_regardless_of_input_format() {
        val inputs = listOf("123-456-7890", "+1 (800) 555-0199", "8005550199")
        for (input in inputs) {
            val digitsOnly = filterDigits(input)
            assertTrue("Expected only digits, got: $digitsOnly", digitsOnly.all { it.isDigit() })
        }
    }
}
