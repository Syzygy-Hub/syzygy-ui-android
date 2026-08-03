package com.syzygyhub.ui.android.tokens

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.Easing as ComposeEasing

/**
 * Motion tokens: durations (milliseconds, for use with `tween(durationMillis = ...)`)
 * and easing curves.
 */
object Animation {
    /** Durations in milliseconds — Compose's `tween`/`AnimationSpec` factories take `Int` millis. */
    object Duration {
        val fast: Int = 150
        val normal: Int = 300
        val slow: Int = 500
    }

    /**
     * Named easing curves. [ComposeEasing] is a simple interpolation curve, but Compose's
     * `spring()` is its own [SpringSpec] `AnimationSpec` type (it models physical
     * motion, not a fixed-duration curve), so it can't be represented as a [ComposeEasing]
     * value like the others — it's exposed as a factory function instead.
     */
    object Easing {
        val standard: ComposeEasing = FastOutSlowInEasing
        val decelerate: ComposeEasing = LinearOutSlowInEasing
        val accelerate: ComposeEasing = FastOutLinearInEasing

        /** A physically-modeled spring [SpringSpec], not a fixed-duration [ComposeEasing] curve. */
        fun <T> spring(): SpringSpec<T> = androidx.compose.animation.core.spring()
    }
}
