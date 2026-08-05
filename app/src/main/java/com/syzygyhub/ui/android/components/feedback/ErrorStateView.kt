package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.components.buttons.PrimaryButton
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * An icon, title, subtitle, and retry action for error states. Mirrors
 * [EmptyStateView]'s structure with a destructive-tinted icon and a
 * mandatory retry action.
 */
@Composable
fun ErrorStateView(
    title: String,
    subtitle: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.ErrorOutline,
    retryLabel: String = "Retry",
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(theme.spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(theme.spacing.sm),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(48.dp),
        )
        Text(text = title, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
        Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
        PrimaryButton(
            text = retryLabel,
            onClick = onRetryClick,
            modifier = Modifier.padding(top = theme.spacing.sm),
        )
    }
}
