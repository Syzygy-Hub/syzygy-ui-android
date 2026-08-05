package com.syzygyhub.ui.android.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalSyzygyTheme = compositionLocalOf { SyzygyTheme.default }

/**
 * Provides a [SyzygyTheme] to the composition tree.
 *
 * ```kotlin
 * SyzygyThemeProvider(theme = SyzygyTheme.dark) {
 *     PrimaryButton(label = "Hello")
 * }
 * ```
 */
@Composable
fun SyzygyThemeProvider(
    theme: SyzygyTheme = SyzygyTheme.default,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalSyzygyTheme provides theme) {
        content()
    }
}

/** Convenience accessor. */
@Composable
fun syzygyTheme(): SyzygyTheme = LocalSyzygyTheme.current
