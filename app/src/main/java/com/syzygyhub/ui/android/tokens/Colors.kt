package com.syzygyhub.ui.android.tokens

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

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

    val ColorScheme.primaryMuted: Color
        @Composable @ReadOnlyComposable
        get() = primary.copy(alpha = 0.12f).compositeOver(surface)

    val ColorScheme.destructiveMuted: Color
        @Composable @ReadOnlyComposable
        get() = error.copy(alpha = 0.12f).compositeOver(surface)

    val ColorScheme.successMuted: Color
        @Composable @ReadOnlyComposable
        get() = success.copy(alpha = 0.12f).compositeOver(surface)

    val ColorScheme.warningMuted: Color
        @Composable @ReadOnlyComposable
        get() = warning.copy(alpha = 0.12f).compositeOver(surface)

    val ColorScheme.surfaceSecondary: Color
        @Composable @ReadOnlyComposable
        get() = surfaceVariant

    val ColorScheme.surfaceTertiary: Color
        @Composable @ReadOnlyComposable
        get() = surfaceVariant.copy(alpha = 0.6f).compositeOver(surface)

    val ColorScheme.textTertiary: Color
        @Composable @ReadOnlyComposable
        get() = onSurfaceVariant.copy(alpha = 0.7f).compositeOver(surface)

    /** A translucent scrim used behind modals/sheets/popovers. */
    val ColorScheme.overlay: Color
        @Composable @ReadOnlyComposable
        get() = scrim.copy(alpha = 0.32f)

    val ColorScheme.link: Color
        @Composable @ReadOnlyComposable
        get() = primary

    val ColorScheme.focus: Color
        @Composable @ReadOnlyComposable
        get() = primary

    val ColorScheme.separator: Color
        @Composable @ReadOnlyComposable
        get() = outlineVariant
}
