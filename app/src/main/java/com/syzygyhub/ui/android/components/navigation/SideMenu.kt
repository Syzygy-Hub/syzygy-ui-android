package com.syzygyhub.ui.android.components.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * A themed wrapper around Material 3's `ModalNavigationDrawer`/`ModalDrawerSheet`
 * (also known as Drawer). Driven by a simple [isOpen]/[onClose] boolean pair rather
 * than requiring the caller to manage `DrawerState` directly — chosen because most
 * consumers just need "is it open", and this matches the value+onValueChange state
 * hoisting convention used by the rest of this library rather than exposing a
 * Compose-specific state object as the public API surface.
 */
@Composable
fun SideMenu(
    isOpen: Boolean,
    onClose: () -> Unit,
    drawerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(isOpen) {
        if (isOpen) drawerState.open() else drawerState.close()
    }
    LaunchedEffect(drawerState.isClosed) {
        if (drawerState.isClosed && isOpen) onClose()
    }

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = MaterialTheme.colorScheme.surface) {
                drawerContent()
            }
        },
        gesturesEnabled = isOpen,
        content = content,
    )
}
