package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp

private val MinTouchTarget = 48.dp

/**
 * A labeled checkbox. Named `CheckboxInput` (not `Checkbox`) to avoid
 * colliding with `androidx.compose.material3.Checkbox`.
 */
@Composable
fun CheckboxInput(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .defaultMinSize(minHeight = MinTouchTarget)
                .semantics {
                    contentDescription = label
                    stateDescription = if (checked) "Checked" else "Unchecked"
                },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = label)
    }
}
