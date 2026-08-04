package com.syzygyhub.ui.android

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.syzygyhub.ui.android.components.buttons.PrimaryButton
import com.syzygyhub.ui.android.components.display.Accordion
import com.syzygyhub.ui.android.components.display.AccordionSection
import com.syzygyhub.ui.android.components.feedback.ConfirmDialog
import com.syzygyhub.ui.android.components.feedback.NetworkStatusBanner
import com.syzygyhub.ui.android.components.inputs.DefaultPhoneCountries
import com.syzygyhub.ui.android.components.inputs.PhoneInput
import com.syzygyhub.ui.android.components.inputs.SearchableDropdown
import com.syzygyhub.ui.android.components.inputs.TextInput
import com.syzygyhub.ui.android.components.navigation.StepIndicator
import com.syzygyhub.ui.android.components.overlay.BottomSheet
import com.syzygyhub.ui.android.components.overlay.ModalDialog
import org.junit.Rule
import org.junit.Test

/**
 * Compose component smoke tests — verifies each component renders without
 * crashing and exposes its primary content to the semantics tree.
 *
 * Note: placed in `src/androidTest/` (instrumented) rather than `src/test/`
 * (local JVM) because `ui-test-junit4` / `ComposeTestRule` require the
 * Android runtime; Robolectric is not present in this project's dependency
 * catalog.
 */
class ComposeComponentSmokeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun primaryButton_rendersWithText() {
        composeTestRule.setContent {
            PrimaryButton(text = "Continue", onClick = {})
        }
        composeTestRule.onNodeWithText("Continue").assertIsDisplayed()
    }

    @Test
    fun textInput_rendersLabel() {
        composeTestRule.setContent {
            TextInput(label = "Email", value = "", onValueChange = {})
        }
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
    }

    @Test
    fun confirmDialog_rendersTitle() {
        composeTestRule.setContent {
            ConfirmDialog(
                title = "Delete item?",
                message = "This action cannot be undone.",
                onConfirm = {},
                onCancel = {},
            )
        }
        composeTestRule.onNodeWithText("Delete item?").assertIsDisplayed()
    }

    @Test
    fun modalDialog_rendersContent() {
        composeTestRule.setContent {
            ModalDialog(onDismissRequest = {}) {
                Text("Dialog content")
            }
        }
        composeTestRule.onNodeWithText("Dialog content").assertIsDisplayed()
    }

    @Test
    fun bottomSheet_rendersContent() {
        composeTestRule.setContent {
            BottomSheet(onDismissRequest = {}) {
                Text("Sheet content")
            }
        }
        composeTestRule.onNodeWithText("Sheet content").assertIsDisplayed()
    }

    @Test
    fun networkStatusBanner_showsWhenForced() {
        composeTestRule.setContent {
            NetworkStatusBanner(manualOverride = false)
        }
        composeTestRule.onNodeWithText("No internet connection").assertIsDisplayed()
    }

    @Test
    fun accordion_rendersSectionTitle() {
        composeTestRule.setContent {
            Accordion(
                sections =
                    listOf(
                        AccordionSection(title = "Shipping") { Text("Ships in 2-3 days.") },
                        AccordionSection(title = "Returns") { Text("30-day return window.") },
                    ),
            )
        }
        composeTestRule.onNodeWithText("Shipping").assertIsDisplayed()
    }

    @Test
    fun phoneInput_rendersDialCode() {
        val us = DefaultPhoneCountries.first()
        composeTestRule.setContent {
            PhoneInput(
                country = us,
                rawNumber = "",
                onCountryChange = {},
                onValueChange = {},
            )
        }
        composeTestRule.onNodeWithText("${us.flagEmoji} ${us.dialCode}").assertIsDisplayed()
    }

    @Test
    fun searchableDropdown_rendersLabel() {
        composeTestRule.setContent {
            SearchableDropdown(
                label = "Country",
                selection = null,
                options = listOf("United States", "Canada", "United Kingdom"),
                onSelectionChange = {},
                optionTitle = { it },
            )
        }
        composeTestRule.onNodeWithText("Country").assertIsDisplayed()
    }

    @Test
    fun stepIndicator_renders() {
        composeTestRule.setContent {
            StepIndicator(
                steps = listOf("Account", "Details", "Confirm"),
                currentStep = 0,
            )
        }
        // StepIndicator renders circles/dots — no text nodes, so we just
        // assert the composable inflates without throwing.
        composeTestRule.waitForIdle()
    }
}
