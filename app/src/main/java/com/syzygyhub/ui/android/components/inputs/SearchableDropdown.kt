package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
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
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * A [Dropdown] variant with an inline search field that filters the visible
 * options as the user types. Built as its own `ExposedDropdownMenuBox`
 * composition (the same rendering approach [Dropdown] uses) rather than
 * wrapping [Dropdown] directly: [Dropdown]'s text field is `readOnly` and
 * shows the current selection's title, whereas this component's text field
 * must stay editable to drive the search query — those are two different
 * roles for the same field, so delegating to [Dropdown] would mean fighting
 * its `readOnly` contract rather than reusing it.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SearchableDropdown(
    label: String,
    selection: T?,
    options: List<T>,
    onSelectionChange: (T) -> Unit,
    optionTitle: (T) -> String,
    modifier: Modifier = Modifier,
    placeholder: String = "Search…",
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    var expanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val filteredOptions =
        remember(query, options) {
            if (query.isBlank()) {
                options
            } else {
                options.filter { optionTitle(it).contains(query, ignoreCase = true) }
            }
        }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier =
            modifier
                .fillMaxWidth()
                .semantics { contentDescription = label },
    ) {
        OutlinedTextField(
            value = if (expanded) query else selection?.let(optionTitle).orEmpty(),
            onValueChange = {
                query = it
                if (!expanded) expanded = true
            },
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            filteredOptions.forEach { option ->
                DropdownMenuItem(
                    text = { Text(optionTitle(option)) },
                    onClick = {
                        onSelectionChange(option)
                        query = ""
                        expanded = false
                    },
                )
            }
        }
    }
}
