package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

private val MinTouchTarget = 48.dp

/**
 * A single labeled radio option. Compose several with shared parent state to
 * build a radio group. Named `RadioButtonInput` (not `RadioButton`) to avoid
 * colliding with `androidx.compose.material3.RadioButton`.
 */
@Composable
fun RadioButtonInput(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Row(
        modifier =
            modifier
                .defaultMinSize(minHeight = MinTouchTarget)
                .selectable(selected = selected, onClick = onClick, role = Role.RadioButton)
                .semantics {
                    contentDescription = label
                    stateDescription = if (selected) "Selected" else "Not selected"
                },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = selected, onClick = null)
        Text(text = label)
    }
}
