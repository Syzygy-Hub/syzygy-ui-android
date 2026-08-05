package com.syzygyhub.ui.android.components.badges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

enum class BadgeVariant { PRIMARY, SUCCESS, WARNING, ERROR }

@Composable
fun Badge(
    text: String,
    variant: BadgeVariant,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val (containerColor, contentColor) =
        when (variant) {
            BadgeVariant.PRIMARY -> MaterialTheme.colorScheme.primary to MaterialTheme.colorScheme.onPrimary
            BadgeVariant.SUCCESS -> Color(0xFF2E7D32) to Color.White
            BadgeVariant.WARNING -> Color(0xFFF9A825) to Color.Black
            BadgeVariant.ERROR -> MaterialTheme.colorScheme.error to MaterialTheme.colorScheme.onError
        }

    Text(
        text = text,
        color = contentColor,
        style = MaterialTheme.typography.labelMedium,
        modifier =
            modifier
                .background(color = containerColor, shape = RoundedCornerShape(theme.radius.full))
                .padding(horizontal = theme.spacing.sm, vertical = theme.spacing.xs)
                .semantics { contentDescription = "${variant.name.lowercase()} badge: $text" },
    )
}
