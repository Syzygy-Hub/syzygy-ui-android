package com.syzygyhub.ui.android.components.feedback

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.Radius

/** An animated skeleton placeholder for list/table rows while content loads. */
@Composable
fun ShimmerView(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = Radius.sm,
) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 700, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        label = "shimmerAlpha",
    )
    val base =
        MaterialTheme.colorScheme.onSurface.copy(
            alpha = alpha
        ).compositeOver(MaterialTheme.colorScheme.surfaceVariant)

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(base)
                .semantics { hideFromAccessibility() },
    )
}
