package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.components.overlay.ModalDialog
import com.syzygyhub.ui.android.tokens.Colors.destructive
import com.syzygyhub.ui.android.tokens.Spacing

/**
 * A preset confirm/cancel modal built on top of [ModalDialog] rather than
 * presenting its own [androidx.compose.ui.window.Dialog] — title, message,
 * and two action buttons. When [isDestructive] is true, the confirm button
 * is styled with the `destructive` color token.
 */
@Composable
fun ConfirmDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
    confirmLabel: String = "Confirm",
    cancelLabel: String = "Cancel",
    isDestructive: Boolean = false,
) {
    ModalDialog(onDismissRequest = onCancel) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = Spacing.sm)
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = Spacing.lg)
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            TextButton(onClick = onCancel) {
                Text(cancelLabel)
            }
            TextButton(
                onClick = onConfirm,
                colors =
                    if (isDestructive) {
                        ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.destructive)
                    } else {
                        ButtonDefaults.textButtonColors()
                    },
            ) {
                Text(confirmLabel)
            }
        }
    }
}
