package com.syzygyhub.ui.android.components.inputs

import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class QuantityStepperTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun increase_button_increments_value() {
        var value = 1
        composeTestRule.setContent {
            QuantityStepper(value = value, onValueChange = { value = it })
        }

        composeTestRule.onNodeWithContentDescription("Increase").performClick()

        assertEquals(2, value)
    }

    @Test
    fun decrease_button_is_disabled_at_range_start() {
        composeTestRule.setContent {
            QuantityStepper(value = 0, onValueChange = {}, range = 0..99)
        }

        composeTestRule.onNodeWithContentDescription("Decrease").assertIsNotEnabled()
    }
}
