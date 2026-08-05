package com.syzygyhub.ui.android.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class SyzygyElevation(val none: Dp, val sm: Dp, val md: Dp, val lg: Dp) {
    companion object {
        val default = SyzygyElevation(none = 0.dp, sm = 1.dp, md = 4.dp, lg = 8.dp)
    }
}
