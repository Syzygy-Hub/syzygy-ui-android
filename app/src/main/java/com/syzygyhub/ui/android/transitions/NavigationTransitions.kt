package com.syzygyhub.ui.android.transitions

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

/**
 * Reusable [ContentTransform] helpers for common navigation/presentation
 * motion, for use with `AnimatedContent`.
 */
object NavigationTransitions {
    enum class HorizontalDirection { LEFT_TO_RIGHT, RIGHT_TO_LEFT }

    /** Slides content in/out horizontally. */
    fun slideTransition(direction: HorizontalDirection): ContentTransform {
        val sign = if (direction == HorizontalDirection.LEFT_TO_RIGHT) 1 else -1
        return (slideInHorizontally(tween(300)) { width -> sign * width } + fadeIn(tween(300)))
            .togetherWith(slideOutHorizontally(tween(300)) { width -> -sign * width } + fadeOut(tween(300)))
    }

    /** A plain cross-fade between the outgoing and incoming content. */
    fun crossFadeTransition(): ContentTransform = fadeIn(tween(300)).togetherWith(fadeOut(tween(300)))

    enum class VerticalDirection { TOP_TO_BOTTOM, BOTTOM_TO_TOP }

    /** Slides content in/out vertically. */
    fun slideVerticalTransition(direction: VerticalDirection): ContentTransform {
        val sign = if (direction == VerticalDirection.TOP_TO_BOTTOM) 1 else -1
        return (slideInVertically(tween(300)) { height -> -sign * height } + fadeIn(tween(300)))
            .togetherWith(slideOutVertically(tween(300)) { height -> sign * height } + fadeOut(tween(300)))
    }

    /** A slide-up-and-fade transition suited to modal/sheet presentation. */
    fun modalPresentationTransition(): ContentTransform =
        (slideInVertically(tween(300)) { height -> height } + fadeIn(tween(300)))
            .togetherWith(slideOutVertically(tween(300)) { height -> height } + fadeOut(tween(300)))

    /** Scales and fades content in/out — suited to dialogs or emphasis changes. */
    fun scaleTransition(): ContentTransform =
        (scaleIn(tween(300), initialScale = 0.9f) + fadeIn(tween(300)))
            .togetherWith(scaleOut(tween(300), targetScale = 0.9f) + fadeOut(tween(300)))

    /**
     * Fades the outgoing content out completely, then fades the incoming content in —
     * a sequential (not simultaneous) cross-fade, staggered via delayed [tween] specs.
     */
    fun fadeThroughTransition(): ContentTransform =
        fadeIn(tween(durationMillis = 210, delayMillis = 90))
            .togetherWith(fadeOut(tween(durationMillis = 90)))
}
