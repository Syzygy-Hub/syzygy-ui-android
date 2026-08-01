package com.syzygyhub.ui.android.components.overlay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.syzygyhub.ui.android.tokens.Spacing

/**
 * A centered dialog card over a dimmed scrim, wrapping [Dialog]. Named
 * `ModalDialog` (not `Dialog`) to avoid colliding with
 * `androidx.compose.ui.window.Dialog`.
 */
@Composable
fun ModalDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 8.dp,
            modifier = Modifier.padding(Spacing.lg),
        ) {
            Column(modifier = Modifier.padding(Spacing.lg)) {
                content()
            }
        }
    }
}
