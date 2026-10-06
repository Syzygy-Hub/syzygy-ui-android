package com.syzygyhub.ui.android.components.inputs

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.text.DecimalFormatSymbols
import java.util.Locale

class CurrencyFormattingTest {
    private val usSymbols = DecimalFormatSymbols.getInstance(Locale.US)

    // --- formatPlain (internal) ---

    @Test
    fun formats_integer_amount_without_decimal() {
        assertEquals("100", formatPlain(100.0, usSymbols))
    }

    @Test
    fun formats_zero_as_zero() {
        assertEquals("0", formatPlain(0.0, usSymbols))
    }

    @Test
    fun formats_decimal_amount_with_two_places() {
        assertEquals("9.99", formatPlain(9.99, usSymbols))
    }

    @Test
    fun formats_large_number_with_thousands_separator() {
        assertEquals("1,000", formatPlain(1_000.0, usSymbols))
        assertEquals("1,000,000", formatPlain(1_000_000.0, usSymbols))
    }

    @Test
    fun formats_negative_amount() {
        assertEquals("-50", formatPlain(-50.0, usSymbols))
    }

    // --- onValueChange normalization (the parsing path mirrors the composable's inline logic) ---

    @Test
    fun normalizes_plain_digits_to_double() {
        val raw = "1234"
        val normalized = raw
            .replace(usSymbols.groupingSeparator.toString(), "")
            .replace(usSymbols.decimalSeparator, '.')
        assertEquals(1234.0, normalized.toDoubleOrNull())
    }

    @Test
    fun normalizes_formatted_value_with_thousands_separator() {
        val raw = "1,000"
        val normalized = raw
            .replace(usSymbols.groupingSeparator.toString(), "")
            .replace(usSymbols.decimalSeparator, '.')
        assertEquals(1000.0, normalized.toDoubleOrNull())
    }

    @Test
    fun returns_null_for_empty_input() {
        val raw = ""
        val normalized = raw
            .replace(usSymbols.groupingSeparator.toString(), "")
            .replace(usSymbols.decimalSeparator, '.')
        assertNull(normalized.toDoubleOrNull())
    }

    @Test
    fun returns_null_for_non_numeric_input() {
        val raw = "abc"
        val normalized = raw
            .replace(usSymbols.groupingSeparator.toString(), "")
            .replace(usSymbols.decimalSeparator, '.')
        assertNull(normalized.toDoubleOrNull())
    }

    // --- Component behaviour: state after parsing ---

    @Test
    fun parsed_value_roundtrips_through_format_and_normalize() {
        val original = 1234.56
        val formatted = formatPlain(original, usSymbols)
        val normalized = formatted
            .replace(usSymbols.groupingSeparator.toString(), "")
            .replace(usSymbols.decimalSeparator, '.')
        assertEquals(original, normalized.toDoubleOrNull())
    }
}
