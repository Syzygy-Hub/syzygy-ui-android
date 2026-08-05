package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/** A tappable field that opens Material 3's [DatePickerDialog], showing the formatted [date] when closed. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    label: String,
    date: LocalDate?,
    onDateChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    var isOpen by remember { mutableStateOf(false) }
    val formatter = remember { DateTimeFormatter.ISO_LOCAL_DATE }
    val selectedDateText = date?.format(formatter) ?: ""

    OutlinedTextField(
        value = selectedDateText,
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { isOpen = true }
                .semantics { contentDescription = "Date picker: $selectedDateText" },
        enabled = false,
        singleLine = true,
        maxLines = 1,
    )

    if (isOpen) {
        val state =
            rememberDatePickerState(
                initialSelectedDateMillis =
                    date?.atStartOfDay(ZoneOffset.UTC)?.toInstant()?.toEpochMilli(),
            )
        DatePickerDialog(
            onDismissRequest = { isOpen = false },
            confirmButton = {
                TextButton(onClick = {
                    state.selectedDateMillis?.let { millis ->
                        onDateChange(
                            Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate(),
                        )
                    }
                    isOpen = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { isOpen = false }) { Text("Cancel") }
            },
        ) {
            DatePicker(state = state)
        }
    }
}
