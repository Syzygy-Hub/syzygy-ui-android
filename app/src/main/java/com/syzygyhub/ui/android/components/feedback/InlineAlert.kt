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
import com.syzygyhub.ui.android.tokens.Colors.destructiveMuted
import com.syzygyhub.ui.android.tokens.Colors.primaryMuted
import com.syzygyhub.ui.android.tokens.Colors.successMuted
import com.syzygyhub.ui.android.tokens.Colors.warningMuted
import com.syzygyhub.ui.android.tokens.Radius
import com.syzygyhub.ui.android.tokens.Spacing

enum class InlineAlertVariant { INFO, SUCCESS, WARNING, ERROR }

/** An inline status banner (also known as Banner) with 4 variants, backgrounded by the *Muted color tokens. */
@Composable
fun InlineAlert(
    message: String,
    variant: InlineAlertVariant,
    modifier: Modifier = Modifier,
) {
    val (background, icon) =
        when (variant) {
            InlineAlertVariant.INFO -> MaterialTheme.colorScheme.primaryMuted to Icons.Filled.Info
            InlineAlertVariant.SUCCESS -> MaterialTheme.colorScheme.successMuted to Icons.Filled.CheckCircle
            InlineAlertVariant.WARNING -> MaterialTheme.colorScheme.warningMuted to Icons.Filled.Warning
            InlineAlertVariant.ERROR -> MaterialTheme.colorScheme.destructiveMuted to Icons.Filled.Error
        }

    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(Radius.md))
                .background(background)
                .padding(Spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(imageVector = icon, contentDescription = variant.name)
        Spacer(modifier = Modifier.width(Spacing.sm))
        Text(text = message, style = MaterialTheme.typography.bodyMedium)
    }
}
