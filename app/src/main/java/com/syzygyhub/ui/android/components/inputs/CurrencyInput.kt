package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

private val MinTouchTarget = 48.dp

/**
 * A numeric text field with a currency symbol prefix (or suffix), formatting
 * the displayed value with locale-appropriate decimal/thousands separators.
 * Formatting uses JDK-native `java.text.NumberFormat.getCurrencyInstance(locale)`
 * — no third-party dependency — defaulting to the device's current
 * [Locale.getDefault] but overridable via [locale]. [value] is the raw
 * numeric amount (Double); the field never exposes the formatted string as
 * its source of truth, only as a display concern.
 */
@Composable
fun CurrencyInput(
    label: String,
    value: Double?,
    onValueChange: (Double?) -> Unit,
    modifier: Modifier = Modifier,
    locale: Locale = Locale.getDefault(),
    symbolPosition: CurrencySymbolPosition = CurrencySymbolPosition.PREFIX,
    enabled: Boolean = true,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val currencyFormat = remember(locale) { NumberFormat.getCurrencyInstance(locale) }
    val symbols = remember(locale) { DecimalFormatSymbols.getInstance(locale) }
    val currencySymbol =
        remember(locale) { currencyFormat.currency?.getSymbol(locale) ?: currencyFormat.currency?.symbol.orEmpty() }

    var text by remember(value, locale) {
        mutableStateOf(value?.let { formatPlain(it, symbols) } ?: "")
    }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            val normalized =
                newText.replace(symbols.groupingSeparator.toString(), "")
                    .replace(symbols.decimalSeparator, '.')
            onValueChange(normalized.toDoubleOrNull())
        },
        label = { Text(label) },
        singleLine = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        leadingIcon =
            if (symbolPosition == CurrencySymbolPosition.PREFIX) {
                { Text(currencySymbol) }
            } else {
                null
            },
        trailingIcon =
            if (symbolPosition == CurrencySymbolPosition.SUFFIX) {
                { Text(currencySymbol) }
            } else {
                null
            },
        modifier =
            modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = MinTouchTarget)
                .semantics { contentDescription = label },
    )
}

/** Where the currency symbol renders relative to a [CurrencyInput]'s numeric field. */
enum class CurrencySymbolPosition { PREFIX, SUFFIX }

private fun formatPlain(
    value: Double,
    symbols: DecimalFormatSymbols,
): String {
    val df = java.text.DecimalFormat("#,##0.##", symbols)
    return df.format(value)
}
