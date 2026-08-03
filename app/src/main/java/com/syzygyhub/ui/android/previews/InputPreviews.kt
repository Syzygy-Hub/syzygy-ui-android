package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syzygyhub.ui.android.components.inputs.CheckboxInput
import com.syzygyhub.ui.android.components.inputs.DatePickerField
import com.syzygyhub.ui.android.components.inputs.Dropdown
import com.syzygyhub.ui.android.components.inputs.FormField
import com.syzygyhub.ui.android.components.inputs.OTPInput
import com.syzygyhub.ui.android.components.inputs.PasswordStrengthIndicator
import com.syzygyhub.ui.android.components.inputs.QuantityStepper
import com.syzygyhub.ui.android.components.inputs.RadioButtonInput
import com.syzygyhub.ui.android.components.inputs.SearchInput
import com.syzygyhub.ui.android.components.inputs.SecureInput
import com.syzygyhub.ui.android.components.inputs.SegmentedControl
import com.syzygyhub.ui.android.components.inputs.SliderInput
import com.syzygyhub.ui.android.components.inputs.TagInput
import com.syzygyhub.ui.android.components.inputs.TextArea
import com.syzygyhub.ui.android.components.inputs.TextInput
import com.syzygyhub.ui.android.components.inputs.TimePickerField
import com.syzygyhub.ui.android.components.inputs.ToggleSwitch
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InputPreviews() {
    SyzygyUiTheme {
        var text by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var bio by remember { mutableStateOf("") }
        var search by remember { mutableStateOf("") }
        var toggle by remember { mutableStateOf(true) }
        var checked by remember { mutableStateOf(false) }
        var selectedRadio by remember { mutableStateOf("A") }
        var sliderValue by remember { mutableStateOf(0.5f) }
        var dropdownSelection by remember { mutableStateOf("Option 1") }
        var segment by remember { mutableStateOf("Day") }
        var quantity by remember { mutableStateOf(1) }
        var bioArea by remember { mutableStateOf("") }
        var otp by remember { mutableStateOf("") }
        var tags by remember { mutableStateOf(listOf("kotlin", "compose")) }
        var date by remember { mutableStateOf<java.time.LocalDate?>(null) }
        var time by remember { mutableStateOf<java.time.LocalTime?>(null) }
        var newPassword by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            TextInput(label = "Email", value = text, onValueChange = { text = it })
            TextInput(label = "Email", value = text, onValueChange = { text = it }, errorMessage = "Invalid email")
            TextInput(label = "Bio", value = bio, onValueChange = { bio = it }, maxLength = 100)
            SecureInput(label = "Password", value = password, onValueChange = { password = it })
            SearchInput(value = search, onValueChange = { search = it })
            ToggleSwitch(label = "Notifications", checked = toggle, onCheckedChange = { toggle = it })
            CheckboxInput(label = "Remember me", checked = checked, onCheckedChange = { checked = it })
            RadioButtonInput(label = "Option A", selected = selectedRadio == "A", onClick = { selectedRadio = "A" })
            RadioButtonInput(label = "Option B", selected = selectedRadio == "B", onClick = { selectedRadio = "B" })
            SliderInput(label = "Volume", value = sliderValue, onValueChange = { sliderValue = it })
            Dropdown(
                label = "Country",
                selection = dropdownSelection,
                options = listOf("Option 1", "Option 2"),
                onSelectionChange = { dropdownSelection = it },
                optionTitle = { it },
            )
            SegmentedControl(
                options = listOf("Day", "Week", "Month"),
                selection = segment,
                onSelectionChange = { segment = it },
                optionTitle = { it },
            )
            QuantityStepper(value = quantity, onValueChange = { quantity = it })
            TextArea(label = "Bio", value = bioArea, onValueChange = { bioArea = it })
            OTPInput(code = otp, onCodeChange = { otp = it })
            TagInput(tags = tags, onTagsChange = { tags = it })
            DatePickerField(label = "Birthdate", date = date, onDateChange = { date = it })
            TimePickerField(label = "Reminder time", time = time, onTimeChange = { time = it })
            FormField(label = "Email", helperText = "We'll never share it") {
                TextInput(label = "Email", value = text, onValueChange = { text = it })
            }
            PasswordStrengthIndicator(password = newPassword)
        }
    }
}
