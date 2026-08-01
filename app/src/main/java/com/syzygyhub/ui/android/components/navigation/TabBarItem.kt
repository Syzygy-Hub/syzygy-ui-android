package com.syzygyhub.ui.android.components.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/** A single destination shown in [TabBar] or [BottomNavigationBar]. */
data class TabBarItem<T>(
    val tag: T,
    val icon: ImageVector,
    val label: String,
)
