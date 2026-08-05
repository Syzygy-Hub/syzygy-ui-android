package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import com.syzygyhub.ui.android.tokens.AppTypography.caption

/**
 * A generic label + content-slot wrapper for form fields, with optional
 * [error] (shown in the Material 3 error color) or [helperText] (shown in
 * `onSurfaceVariant` when [error] is null).
 */
@Composable
fun FormField(
    label: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    helperText: String? = null,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(theme.spacing.xs))
        content()
        val message = error ?: helperText
        if (message != null) {
            Text(
                text = message,
                style = MaterialTheme.typography.caption,
                color =
                    if (error != null) {
                        MaterialTheme.colorScheme.error
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
            )
        }
    }
}
