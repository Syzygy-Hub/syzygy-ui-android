package com.syzygyhub.ui.android.components.feedback

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A circular progress indicator supporting both determinate ([progress] non-null,
 * a partial ring) and indeterminate ([progress] null) modes.
 */
@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    progress: Float? = null,
) {
    if (progress != null) {
        CircularProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = modifier,
            color = MaterialTheme.colorScheme.primary,
        )
    } else {
        CircularProgressIndicator(
            modifier = modifier,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
