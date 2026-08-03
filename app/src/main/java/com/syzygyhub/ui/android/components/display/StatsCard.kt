package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.tokens.Spacing

enum class Trend { UP, DOWN, NEUTRAL }

/**
 * A card showing a [label], a large [value], and an optional [trend] indicator
 * with a [trendValue] delta (e.g. "+12%"). Also known as MetricCard.
 */
@Composable
fun StatsCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    trend: Trend? = null,
    trendValue: String? = null,
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(Spacing.md)) {
            Text(text = label, style = MaterialTheme.typography.labelMedium)
            Text(text = value, style = MaterialTheme.typography.headlineMedium)
            if (trend != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val (icon, color) =
                        when (trend) {
                            Trend.UP -> Icons.Filled.ArrowUpward to MaterialTheme.colorScheme.primary
                            Trend.DOWN -> Icons.Filled.ArrowDownward to MaterialTheme.colorScheme.error
                            Trend.NEUTRAL -> Icons.Filled.Remove to MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    Icon(
                        imageVector = icon,
                        contentDescription = trend.name,
                        tint = color,
                        modifier = Modifier.padding(end = Spacing.xxs),
                    )
                    if (trendValue != null) {
                        Text(text = trendValue, style = MaterialTheme.typography.labelMedium, color = color)
                    }
                }
            }
        }
    }
}
