package com.syzygyhub.ui.android.tokens

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle

/**
 * Semantic type scale built on top of Material 3 [Typography].
 * Consumers should reference these instead of building [TextStyle]s manually.
 */
object AppTypography {
    val Typography.display: TextStyle
        @Composable @ReadOnlyComposable
        get() = displayLarge

    val Typography.headline: TextStyle
        @Composable @ReadOnlyComposable
        get() = headlineSmall

    val Typography.title: TextStyle
        @Composable @ReadOnlyComposable
        get() = titleMedium

    val Typography.body: TextStyle
        @Composable @ReadOnlyComposable
        get() = bodyMedium

    val Typography.label: TextStyle
        @Composable @ReadOnlyComposable
        get() = labelMedium

    val Typography.caption: TextStyle
        @Composable @ReadOnlyComposable
        get() = labelSmall
}
