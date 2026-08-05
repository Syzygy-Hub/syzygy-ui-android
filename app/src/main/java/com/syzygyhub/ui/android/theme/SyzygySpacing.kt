package com.syzygyhub.ui.android.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class SyzygySpacing(
    val xxs: Dp,
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val xxl: Dp,
    val xxxl: Dp,
) {
    companion object {
        val default =
            SyzygySpacing(
                xxs = 2.dp,
                xs = 4.dp,
                sm = 8.dp,
                md = 16.dp,
                lg = 24.dp,
                xl = 32.dp,
                xxl = 48.dp,
                xxxl = 64.dp,
            )
    }
}
