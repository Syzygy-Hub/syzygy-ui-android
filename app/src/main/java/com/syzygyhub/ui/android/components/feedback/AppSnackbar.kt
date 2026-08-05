package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import kotlinx.coroutines.delay

/**
 * A themed, opinionated snackbar. Named `AppSnackbar` to avoid colliding with Material 3's
 * own `Snackbar` composable. This library favors small, no-hidden-global-state composables
 * (as [com.syzygyhub.ui.android.components.overlay.BottomSheet]/`ModalDialog` do), so rather
 * than wiring into Material 3's `SnackbarHost`/`SnackbarHostState` system, this is a standalone
 * presentational composable: a consumer conditionally shows it in a `Box` with
 * `Modifier.align(Alignment.BottomCenter)`, and it auto-dismisses itself via [LaunchedEffect].
 */
@Composable
fun AppSnackbar(
    message: String,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    durationMillis: Long = 4000,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    LaunchedEffect(isVisible) {
        if (isVisible) {
            delay(durationMillis)
            onDismiss()
        }
    }

    if (isVisible) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(theme.radius.md),
            color = MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = theme.spacing.md, vertical = theme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = message, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
