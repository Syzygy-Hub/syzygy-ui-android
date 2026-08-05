package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import com.syzygyhub.ui.android.tokens.Colors

enum class InlineAlertVariant { INFO, SUCCESS, WARNING, ERROR }

/** An inline status banner (also known as Banner) with 4 variants, backgrounded by the *Muted color tokens. */
@Composable
fun InlineAlert(
    message: String,
    variant: InlineAlertVariant,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val (background, icon) =
        with(Colors) {
            when (variant) {
                InlineAlertVariant.INFO -> MaterialTheme.colorScheme.primaryMuted to Icons.Filled.Info
                InlineAlertVariant.SUCCESS -> MaterialTheme.colorScheme.successMuted to Icons.Filled.CheckCircle
                InlineAlertVariant.WARNING -> MaterialTheme.colorScheme.warningMuted to Icons.Filled.Warning
                InlineAlertVariant.ERROR -> MaterialTheme.colorScheme.destructiveMuted to Icons.Filled.Error
            }
        }

    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(theme.radius.md))
                .background(background)
                .padding(theme.spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(imageVector = icon, contentDescription = variant.name)
        Spacer(modifier = Modifier.width(theme.spacing.sm))
        Text(text = message, style = MaterialTheme.typography.bodyMedium)
    }
}
