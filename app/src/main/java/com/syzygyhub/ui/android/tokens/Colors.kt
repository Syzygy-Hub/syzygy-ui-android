package com.syzygyhub.ui.android.tokens

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

/**
 * Semantic color tokens layered on top of the Material 3 [ColorScheme].
 * Consumers must reference these instead of hardcoding colors so that
 * Dark Mode and dynamic theming are honored automatically.
 */
object Colors {
    val ColorScheme.success: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFF2E7D32)

    val ColorScheme.onSuccess: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFFFFFFFF)

    val ColorScheme.warning: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFFF9A825)

    val ColorScheme.onWarning: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFF000000)

    val ColorScheme.danger: Color
        @Composable @ReadOnlyComposable
        get() = error

    val ColorScheme.onDanger: Color
        @Composable @ReadOnlyComposable
        get() = onError
}
