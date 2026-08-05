package com.syzygyhub.ui.android.theme

/**
 * Root theme object. Provide with [SyzygyThemeProvider]; access via [LocalSyzygyTheme].
 */
data class SyzygyTheme(
    val colors: SyzygyColors,
    val radius: SyzygyRadius,
    val typography: SyzygyTypography,
    val spacing: SyzygySpacing,
    val elevation: SyzygyElevation,
    val animation: SyzygyAnimation,
) {
    companion object {
        val default =
            SyzygyTheme(
                colors = SyzygyColors.default,
                radius = SyzygyRadius.default,
                typography = SyzygyTypography.default,
                spacing = SyzygySpacing.default,
                elevation = SyzygyElevation.default,
                animation = SyzygyAnimation.default,
            )
        val dark =
            SyzygyTheme(
                colors = SyzygyColors.dark,
                radius = SyzygyRadius.default,
                typography = SyzygyTypography.default,
                spacing = SyzygySpacing.default,
                elevation = SyzygyElevation.default,
                animation = SyzygyAnimation.default,
            )
        val highContrast =
            SyzygyTheme(
                colors = SyzygyColors.highContrast,
                radius = SyzygyRadius.sharp,
                typography = SyzygyTypography.highContrast,
                spacing = SyzygySpacing.default,
                elevation = SyzygyElevation.default,
                animation = SyzygyAnimation.default,
            )
    }
}
