package com.syzygyhub.ui.android.tokens

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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

    /** A large display style for hero/title screens, bolder and larger than [display]. */
    val Typography.largeTitle: TextStyle
        @Composable @ReadOnlyComposable
        get() = displayLarge.copy(fontSize = 34.sp, fontWeight = FontWeight.Bold)
}
