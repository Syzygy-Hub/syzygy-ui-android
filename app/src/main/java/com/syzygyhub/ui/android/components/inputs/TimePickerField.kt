package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.syzygyhub.ui.android.tokens.Radius
import com.syzygyhub.ui.android.tokens.Spacing
import java.time.LocalTime

/** A tappable field that opens Material 3's [TimePicker] in a dialog, showing the formatted [time] when closed. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerField(
    label: String,
    time: LocalTime?,
    onTimeChange: (LocalTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isOpen by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = time?.let { "%02d:%02d".format(it.hour, it.minute) } ?: "",
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { isOpen = true },
        enabled = false,
        singleLine = true,
    )

    if (isOpen) {
        val state =
            rememberTimePickerState(
                initialHour = time?.hour ?: 0,
                initialMinute = time?.minute ?: 0,
            )
        Dialog(onDismissRequest = { isOpen = false }) {
            Surface(shape = RoundedCornerShape(Radius.lg)) {
                Column(modifier = Modifier.padding(Spacing.md)) {
                    TimePicker(state = state)
                    Row {
                        TextButton(onClick = { isOpen = false }) { Text("Cancel") }
                        TextButton(onClick = {
                            onTimeChange(LocalTime.of(state.hour, state.minute))
                            isOpen = false
                        }) { Text("OK") }
                    }
                }
            }
        }
    }
}
