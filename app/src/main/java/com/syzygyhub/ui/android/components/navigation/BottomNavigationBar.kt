package com.syzygyhub.ui.android.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.Spacing

/**
 * A floating, icon-only bottom navigation pill — a visual alternative to
 * [TabBar] for screens that want a compact, inset navigation surface.
 */
@Composable
fun <T> BottomNavigationBar(
    items: List<TabBarItem<T>>,
    selection: T,
    onSelectionChange: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
    ) {
        Row(modifier = Modifier.padding(horizontal = Spacing.sm, vertical = Spacing.xs)) {
            items.forEach { item ->
                val selected = item.tag == selection
                IconButton(
                    onClick = { onSelectionChange(item.tag) },
                    modifier =
                        Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(if (selected) MaterialTheme.colorScheme.primary else Color.Transparent),
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
                }
            }
        }
    }
}
