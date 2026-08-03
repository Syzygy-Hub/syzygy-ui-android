package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/** A single selectable country/dial-code entry for [PhoneInput]. */
data class PhoneCountry(
    val name: String,
    val flagEmoji: String,
    val dialCode: String,
    val isoCode: String,
)

/**
 * A deliberately minimal starter set of real countries (name, flag emoji,
 * dial code, ISO code) — 15 entries covering the most commonly needed
 * markets. Not exhaustive; pass your own list via [PhoneInput]'s `countries`
 * param (e.g. a fuller ISO-3166 table) to override without forking this file.
 */
val DefaultPhoneCountries =
    listOf(
        PhoneCountry("United States", "🇺🇸", "+1", "US"),
        PhoneCountry("Canada", "🇨🇦", "+1", "CA"),
        PhoneCountry("United Kingdom", "🇬🇧", "+44", "GB"),
        PhoneCountry("Ireland", "🇮🇪", "+353", "IE"),
        PhoneCountry("Australia", "🇦🇺", "+61", "AU"),
        PhoneCountry("New Zealand", "🇳🇿", "+64", "NZ"),
        PhoneCountry("Germany", "🇩🇪", "+49", "DE"),
        PhoneCountry("France", "🇫🇷", "+33", "FR"),
        PhoneCountry("Spain", "🇪🇸", "+34", "ES"),
        PhoneCountry("Italy", "🇮🇹", "+39", "IT"),
        PhoneCountry("India", "🇮🇳", "+91", "IN"),
        PhoneCountry("Japan", "🇯🇵", "+81", "JP"),
        PhoneCountry("China", "🇨🇳", "+86", "CN"),
        PhoneCountry("Brazil", "🇧🇷", "+55", "BR"),
        PhoneCountry("Mexico", "🇲🇽", "+52", "MX"),
    )

/**
 * A phone number text field with a tappable country-code prefix selector
 * (flag emoji + dial code) and a numeric keyboard. Exposes both a
 * [onFormattedChange] display string (`"{dialCode} {rawDigits}"`) and an
 * [onValueChange] digits-only raw number, so consumers can persist/validate
 * the raw value while displaying the formatted one.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneInput(
    country: PhoneCountry,
    rawNumber: String,
    onCountryChange: (PhoneCountry) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Phone number",
    countries: List<PhoneCountry> = DefaultPhoneCountries,
    onFormattedChange: ((String) -> Unit)? = null,
) {
    var expanded by remember { mutableStateOf(false) }

    Row(modifier = modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.width(112.dp),
        ) {
            OutlinedTextField(
                value = "${country.flagEmoji} ${country.dialCode}",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier =
                    Modifier
                        .width(112.dp)
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .semantics { contentDescription = "Country code, ${country.name} ${country.dialCode}" },
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                countries.forEach { entry ->
                    DropdownMenuItem(
                        text = { Text("${entry.flagEmoji} ${entry.name} (${entry.dialCode})") },
                        onClick = {
                            onCountryChange(entry)
                            expanded = false
                        },
                    )
                }
            }
        }
        OutlinedTextField(
            value = rawNumber,
            onValueChange = { newValue ->
                val digitsOnly = newValue.filter { it.isDigit() }
                onValueChange(digitsOnly)
                onFormattedChange?.invoke("${country.dialCode} $digitsOnly")
            },
            label = { Text(label) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier =
                Modifier
                    .weight(1f)
                    .defaultMinSize(minHeight = 48.dp)
                    .padding(start = 8.dp),
        )
    }
}
