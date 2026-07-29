package com.aks.android_ui_library.components.inputs

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class TextInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shows_character_counter_when_maxLength_is_set() {
        composeTestRule.setContent {
            TextInput(label = "Bio", value = "Hello", onValueChange = {}, maxLength = 100)
        }

        composeTestRule.onNodeWithText("5/100").assertExists()
    }

    @Test
    fun hides_character_counter_when_maxLength_is_not_set() {
        composeTestRule.setContent {
            TextInput(label = "Bio", value = "Hello", onValueChange = {})
        }

        composeTestRule.onNodeWithText("5/100").assertDoesNotExist()
    }
}
