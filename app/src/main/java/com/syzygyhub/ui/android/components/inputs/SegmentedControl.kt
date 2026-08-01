package com.syzygyhub.ui.android.components.inputs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

/**
 * An inline, single-row segmented picker for switching between a small set
 * of content states — distinct from `TabBar`/`BottomNavigationBar`, which
 * are for primary app navigation, not in-place content switching.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SegmentedControl(
    options: List<T>,
    selection: T,
    onSelectionChange: (T) -> Unit,
    optionTitle: (T) -> String,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                selected = option == selection,
                onClick = { onSelectionChange(option) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                modifier = Modifier.semantics { contentDescription = optionTitle(option) },
            ) {
                Text(optionTitle(option))
            }
        }
    }
}
