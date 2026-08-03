package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.Spacing

private val DotSize = 8.dp

/**
 * A row of dots indicating the current page position, syncing with
 * [com.syzygyhub.ui.android.components.navigation.PagerView]. Also known as
 * DotIndicator. Read-only display — this does not support tap-to-navigate,
 * matching a page indicator's usual role as a passive status readout; wrap it
 * alongside your own `HorizontalPager`/`PagerView` if you need tap-to-jump.
 */
@Composable
fun PageControl(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    Row(
        modifier = modifier.semantics { contentDescription = "Page ${currentPage + 1} of $pageCount" },
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier =
                    Modifier
                        .size(DotSize)
                        .clip(CircleShape)
                        .background(if (index == currentPage) activeColor else inactiveColor),
            )
            if (index != pageCount - 1) {
                Spacer(modifier = Modifier.size(Spacing.xxs))
            }
        }
    }
}
