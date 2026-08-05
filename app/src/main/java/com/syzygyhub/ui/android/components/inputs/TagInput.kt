package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.syzygyhub.ui.android.components.display.Chip
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** A text input that renders entered [tags] as dismissible [Chip]s. */
@Composable
fun TagInput(
    tags: List<String>,
    onTagsChange: (List<String>) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Add tag",
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    var draft by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        if (tags.isNotEmpty()) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(theme.spacing.xs),
                verticalArrangement = Arrangement.spacedBy(theme.spacing.xs),
            ) {
                tags.forEach { tag ->
                    Chip(text = tag, onRemove = { onTagsChange(tags - tag) })
                }
            }
        }
        OutlinedTextField(
            value = draft,
            onValueChange = { draft = it },
            label = { Text(label) },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = theme.spacing.sm),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions =
                KeyboardActions(
                    onDone = {
                        val trimmed = draft.trim()
                        if (trimmed.isNotEmpty() && !tags.contains(trimmed)) {
                            onTagsChange(tags + trimmed)
                        }
                        draft = ""
                    },
                ),
        )
    }
}
