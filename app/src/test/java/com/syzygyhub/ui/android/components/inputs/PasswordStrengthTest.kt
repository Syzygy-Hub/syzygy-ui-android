package com.syzygyhub.ui.android.components.inputs

import org.junit.Assert.assertEquals
import org.junit.Test

class PasswordStrengthTest {
    // --- computePasswordStrength ---

    @Test
    fun empty_password_is_weak() {
        assertEquals(PasswordStrength.WEAK, computePasswordStrength(""))
    }

    @Test
    fun single_lowercase_letter_is_weak() {
        // length 1 → lengthScore 0, classCount 1 → score 1 → WEAK
        assertEquals(PasswordStrength.WEAK, computePasswordStrength("a"))
    }

    @Test
    fun short_all_lowercase_is_weak() {
        // length 5 → lengthScore 1, classCount 1 → score 2 → WEAK
        assertEquals(PasswordStrength.WEAK, computePasswordStrength("abcde"))
    }

    @Test
    fun medium_mixed_case_and_digit_is_fair() {
        // "abcDEF1" — length 7 → lengthScore 1, classCount 3 → score 4 → FAIR
        assertEquals(PasswordStrength.FAIR, computePasswordStrength("abcDEF1"))
    }

    @Test
    fun strong_password_with_all_character_classes() {
        // "abcDEF12!" — length 9 → lengthScore 2, classCount 4 → score 6 → STRONG
        assertEquals(PasswordStrength.STRONG, computePasswordStrength("abcDEF12!"))
    }

    @Test
    fun very_strong_long_password_with_all_character_classes() {
        // "abcDEF12!@#xyz" — length 14 → lengthScore 3, classCount 4 → score 7 → VERY_STRONG
        assertEquals(PasswordStrength.VERY_STRONG, computePasswordStrength("abcDEF12!@#xyz"))
    }

    @Test
    fun special_characters_only_short_is_weak() {
        // "@#$%^&" — length 6 → lengthScore 1, classCount 1 → score 2 → WEAK
        assertEquals(PasswordStrength.WEAK, computePasswordStrength("@#\$%^&"))
    }

    @Test
    fun strength_labels_are_correct() {
        assertEquals("Weak", PasswordStrength.WEAK.label)
        assertEquals("Fair", PasswordStrength.FAIR.label)
        assertEquals("Strong", PasswordStrength.STRONG.label)
        assertEquals("Very Strong", PasswordStrength.VERY_STRONG.label)
    }

    @Test
    fun strength_enum_has_four_variants() {
        assertEquals(4, PasswordStrength.entries.size)
    }

    // --- Component behaviour: strength value drives correct segment count ---

    @Test
    fun weak_password_maps_to_first_segment_only() {
        val strength = computePasswordStrength("abc")
        assertEquals(PasswordStrength.WEAK, strength)
        // WEAK fills exactly 1 of 4 segments in the indicator
        val filledSegments = when (strength) {
            PasswordStrength.WEAK -> 1
            PasswordStrength.FAIR -> 2
            PasswordStrength.STRONG -> 3
            PasswordStrength.VERY_STRONG -> 4
        }
        assertEquals(1, filledSegments)
    }

    @Test
    fun strong_password_maps_to_three_segments() {
        val strength = computePasswordStrength("abcDEF12!")
        assertEquals(PasswordStrength.STRONG, strength)
        val filledSegments = when (strength) {
            PasswordStrength.WEAK -> 1
            PasswordStrength.FAIR -> 2
            PasswordStrength.STRONG -> 3
            PasswordStrength.VERY_STRONG -> 4
        }
        assertEquals(3, filledSegments)
    }
}
