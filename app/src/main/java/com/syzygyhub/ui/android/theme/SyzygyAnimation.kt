package com.syzygyhub.ui.android.theme

data class SyzygyAnimation(
    val durationFast: Int = 150,
    val durationNormal: Int = 300,
    val durationSlow: Int = 500,
) {
    companion object {
        val default = SyzygyAnimation()
    }
}
