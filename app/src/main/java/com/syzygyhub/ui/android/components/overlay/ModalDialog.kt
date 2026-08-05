package com.syzygyhub.ui.android.components.overlay

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * A centered dialog card over a dimmed scrim, wrapping [Popup]. Named
 * `ModalDialog` (not `Dialog`) to avoid colliding with
 * `androidx.compose.ui.window.Dialog`.
 *
 * Uses a [Popup]-based implementation so that the scrim color is sourced from
 * [SyzygyTheme.colors.overlay] / [SyzygyTheme.colors.overlayAlpha] rather
 * than the opaque internal scrim produced by
 * `androidx.compose.ui.window.Dialog` (which does not expose a scrimColor
 * parameter in the Compose BOM version used by this project).
 */
@Composable
fun ModalDialog(
    onDismissRequest: () -> Unit,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Popup(
        onDismissRequest = onDismissRequest,
        properties =
            PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Scrim layer — color sourced from theme.
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = theme.colors.overlayAlpha))
                        .clickable(onClick = onDismissRequest),
            )
            // Dialog card — centered over the scrim.
            Box(
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = theme.spacing.lg),
            ) {
                Surface(
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = 8.dp,
                    modifier = Modifier.padding(theme.spacing.lg),
                ) {
                    Column(modifier = Modifier.padding(theme.spacing.lg)) {
                        content()
                    }
                }
            }
        }
    }
}
