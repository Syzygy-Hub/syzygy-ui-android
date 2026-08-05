package com.syzygyhub.ui.android.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * A floating pill-style bar showing both icon AND label per item — distinct from
 * [BottomNavigationBar] (floating, icon-only) and [TabBar] (edge-to-edge, labeled).
 * This fills the remaining cell in the {edge-to-edge vs floating} x {icon-only vs
 * icon+label} matrix: floating + labeled.
 */
@Composable
fun <T> FloatingTabBar(
    items: List<TabBarItem<T>>,
    selection: T,
    onSelectionChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Surface(
        modifier = modifier,
        shape = CircleShape,
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
    ) {
        Row(modifier = Modifier.padding(horizontal = theme.spacing.sm, vertical = theme.spacing.xs)) {
            items.forEach { item ->
                val selected = item.tag == selection
                Column(
                    modifier =
                        Modifier
                            .clip(CircleShape)
                            .background(if (selected) MaterialTheme.colorScheme.primary else Color.Transparent)
                            .clickable { onSelectionChange(item.tag) }
                            .padding(horizontal = theme.spacing.md, vertical = theme.spacing.xs),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint =
                            if (selected) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                    )
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall,
                        color =
                            if (selected) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                    )
                }
            }
        }
    }
}
