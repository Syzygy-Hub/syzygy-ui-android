package com.syzygyhub.ui.android.components.buttons

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * A horizontal segmented button row. Pass [multiSelect] to allow more than one
 * option selected at once; single-select mode constrains [selection] to size 1.
 */
@Composable
fun ButtonGroup(
    options: List<String>,
    selection: List<Int>,
    onSelectionChange: (List<Int>) -> Unit,
    modifier: Modifier = Modifier,
    multiSelect: Boolean = false,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    if (multiSelect) {
        MultiChoiceSegmentedButtonRow(modifier = modifier) {
            options.forEachIndexed { index, option ->
                val isSelected = selection.contains(index)
                SegmentedButton(
                    checked = isSelected,
                    onCheckedChange = { checked ->
                        val next = if (checked) selection + index else selection - index
                        onSelectionChange(next)
                    },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    label = { Text(option) },
                    modifier =
                        Modifier.semantics {
                            role = Role.Button
                            contentDescription = option
                        },
                )
            }
        }
    } else {
        SingleChoiceSegmentedButtonRow(modifier = modifier) {
            options.forEachIndexed { index, option ->
                SegmentedButton(
                    selected = selection.contains(index),
                    onClick = { onSelectionChange(listOf(index)) },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    colors = SegmentedButtonDefaults.colors(activeContainerColor = MaterialTheme.colorScheme.primary),
                    label = { Text(option) },
                    modifier =
                        Modifier.semantics {
                            role = Role.Button
                            contentDescription = option
                        },
                )
            }
        }
    }
}
