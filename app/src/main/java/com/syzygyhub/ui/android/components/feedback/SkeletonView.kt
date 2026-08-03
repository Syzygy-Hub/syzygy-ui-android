package com.syzygyhub.ui.android.components.feedback

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import com.syzygyhub.ui.android.tokens.Radius

enum class SkeletonShape { RECTANGLE, CIRCLE }

/**
 * A shimmering placeholder that stands in for arbitrary content while loading,
 * mirroring [ShimmerView]'s shimmer animation but parameterized by [shape] and
 * explicit [width]/[height] rather than being fixed to a single-line row shape.
 */
@Composable
fun SkeletonView(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    shape: SkeletonShape = SkeletonShape.RECTANGLE,
    cornerRadius: Dp = Radius.sm,
) {
    val transition = rememberInfiniteTransition(label = "skeleton")
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 700, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        label = "skeletonAlpha",
    )
    val base =
        MaterialTheme.colorScheme.onSurface.copy(alpha = alpha)
            .compositeOver(MaterialTheme.colorScheme.surfaceVariant)
    val clipShape = if (shape == SkeletonShape.CIRCLE) CircleShape else RoundedCornerShape(cornerRadius)

    Box(
        modifier =
            modifier
                .size(width, height)
                .clip(clipShape)
                .background(base)
                .semantics { hideFromAccessibility() },
    )
}
