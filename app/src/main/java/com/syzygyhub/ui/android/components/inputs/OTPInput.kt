package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** A row of [length] fixed single-character boxes for OTP/PIN entry, auto-advancing focus. */
@Composable
fun OTPInput(
    code: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    length: Int = 6,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val focusRequesters = remember(length) { List(length) { FocusRequester() } }
    val digits = remember(length) { mutableStateListOf(*Array(length) { "" }) }

    LaunchedEffect(code, length) {
        for (i in 0 until length) {
            digits[i] = code.getOrNull(i)?.toString() ?: ""
        }
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(theme.spacing.sm)) {
        for (i in 0 until length) {
            OutlinedTextField(
                value = TextFieldValue(digits[i], selection = TextRange(digits[i].length)),
                onValueChange = { newValue ->
                    val char = newValue.text.trim().takeLast(1)
                    digits[i] = char
                    onCodeChange(digits.joinToString(""))
                    if (char.isNotEmpty() && i < length - 1) {
                        focusRequesters[i + 1].requestFocus()
                    }
                },
                modifier =
                    Modifier
                        .size(48.dp)
                        .focusable()
                        .focusRequester(focusRequesters[i]),
                singleLine = true,
                textStyle = TextStyle(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            )
        }
    }
}
