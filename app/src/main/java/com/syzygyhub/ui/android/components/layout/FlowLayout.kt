package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import com.syzygyhub.ui.android.tokens.Spacing

/**
 * A wrapping row for chips/tags that flows to the next line when full. Wraps
 * Compose Foundation's native `FlowRow` (available in this project's Compose
 * Foundation version) rather than reimplementing wrapping layout from scratch.
 */
@Composable
fun FlowLayout(
    modifier: Modifier = Modifier,
    horizontalSpacing: Dp = Spacing.sm,
    verticalSpacing: Dp = Spacing.sm,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
    ) {
        content()
    }
}
