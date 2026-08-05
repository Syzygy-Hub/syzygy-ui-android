package com.syzygyhub.ui.android.components.overlay

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

data class ActionSheetAction(
    val label: String,
    val isDestructive: Boolean = false,
    val onClick: () -> Unit,
)

/**
 * A bottom-anchored sheet listing labelled [actions], following the same
 * [ModalBottomSheet]-based presentation convention as [BottomSheet].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionSheet(
    actions: List<ActionSheetAction>,
    onDismissRequest: () -> Unit,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(),
    ) {
        Column(modifier = Modifier.padding(bottom = theme.spacing.lg)) {
            actions.forEach { action ->
                Text(
                    text = action.label,
                    color =
                        if (action.isDestructive) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                    style = MaterialTheme.typography.bodyLarge,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp)
                            .clickable {
                                action.onClick()
                                onDismissRequest()
                            }
                            .padding(horizontal = theme.spacing.lg, vertical = theme.spacing.md),
                )
            }
        }
    }
}
