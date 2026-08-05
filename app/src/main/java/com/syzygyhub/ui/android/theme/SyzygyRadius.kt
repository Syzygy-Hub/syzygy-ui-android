package com.syzygyhub.ui.android.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class SyzygyRadius(
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val full: Dp,
) {
    companion object {
        val default = SyzygyRadius(xs = 2.dp, sm = 4.dp, md = 8.dp, lg = 16.dp, xl = 24.dp, full = 9999.dp)
        val sharp = SyzygyRadius(xs = 0.dp, sm = 0.dp, md = 0.dp, lg = 0.dp, xl = 0.dp, full = 0.dp)
    }
}
