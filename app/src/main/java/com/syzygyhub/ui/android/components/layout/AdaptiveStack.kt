package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Arranges [content] horizontally (a [Row]) when available width is at least
 * [breakpoint], and vertically (a [Column]) below it — read via [BoxWithConstraints].
 */
@Composable
fun AdaptiveStack(
    breakpoint: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier = modifier) {
        if (maxWidth >= breakpoint) {
            Row { content() }
        } else {
            Column { content() }
        }
    }
}
