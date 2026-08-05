package com.syzygyhub.ui.android.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * An edge-to-edge, icon-and-label tab bar, wrapping Material 3
 * [NavigationBar]. Named `TabBar` for cross-platform parity with the rest
 * of the Syzygy ecosystem.
 */
@Composable
fun <T> TabBar(
    items: List<TabBarItem<T>>,
    selection: T,
    onSelectionChange: (T) -> Unit,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.tag == selection,
                onClick = { onSelectionChange(item.tag) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
            )
        }
    }
}
