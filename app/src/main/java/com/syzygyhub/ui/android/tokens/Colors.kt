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
 *
 * M3 `ColorScheme` already provides: `primary`, `onPrimary`, `error`,
 * `onError`, `surface`, `onSurface`, `background`, `onBackground`,
 * `outline` (≈ border), `scrim`, etc. — these extension properties add
 * the semantic tokens that M3 doesn't supply directly.
 */
object Colors {
    // MARK: — Success

    val ColorScheme.success: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFF2E7D32)

    val ColorScheme.onSuccess: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFFFFFFFF)

    // MARK: — Warning

    val ColorScheme.warning: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFFF9A825)

    val ColorScheme.onWarning: Color
        @Composable @ReadOnlyComposable
        get() = Color(0xFF000000)

    // MARK: — Destructive (delete / irreversible actions)
    //
    // `destructive` delegates to M3's `error` so that dynamic colour
    // and accessibility contrast guarantees apply automatically.

    val ColorScheme.destructive: Color
        @Composable @ReadOnlyComposable
        get() = error

    val ColorScheme.onDestructive: Color
        @Composable @ReadOnlyComposable
        get() = onError

    // MARK: — Muted tints

    val ColorScheme.primaryMuted: Color
        @Composable @ReadOnlyComposable
        get() = primary.copy(alpha = 0.12f).compositeOver(surface)

    /** Lighter tint of `primary` for large-area background washes. */
    val ColorScheme.primarySubtle: Color
        @Composable @ReadOnlyComposable
        get() = primary.copy(alpha = 0.06f).compositeOver(surface)

    val ColorScheme.errorMuted: Color
        @Composable @ReadOnlyComposable
        get() = error.copy(alpha = 0.12f).compositeOver(surface)

    val ColorScheme.destructiveMuted: Color
        @Composable @ReadOnlyComposable
        get() = error.copy(alpha = 0.12f).compositeOver(surface)

    val ColorScheme.successMuted: Color
        @Composable @ReadOnlyComposable
        get() = success.copy(alpha = 0.12f).compositeOver(surface)

    val ColorScheme.warningMuted: Color
        @Composable @ReadOnlyComposable
        get() = warning.copy(alpha = 0.12f).compositeOver(surface)

    // MARK: — Surface variants

    val ColorScheme.surfaceSecondary: Color
        @Composable @ReadOnlyComposable
        get() = surfaceVariant

    val ColorScheme.surfaceTertiary: Color
        @Composable @ReadOnlyComposable
        get() = surfaceVariant.copy(alpha = 0.6f).compositeOver(surface)

    // MARK: — Text

    val ColorScheme.textTertiary: Color
        @Composable @ReadOnlyComposable
        get() = onSurfaceVariant.copy(alpha = 0.7f).compositeOver(surface)

    val ColorScheme.textDisabled: Color
        @Composable @ReadOnlyComposable
        get() = onSurface.copy(alpha = 0.38f)

    val ColorScheme.textInverse: Color
        @Composable @ReadOnlyComposable
        get() = surface

    // MARK: — UI

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
