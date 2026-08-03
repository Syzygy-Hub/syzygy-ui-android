package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.tokens.AppTypography.caption
import com.syzygyhub.ui.android.tokens.Spacing

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
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(Spacing.xs))
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
