package com.syzygyhub.ui.android.components.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import com.syzygyhub.ui.android.tokens.Colors

/** A horizontal trail of tappable navigation labels, separated by a "/" glyph. */
@Composable
fun Breadcrumbs(
    items: List<Pair<String, () -> Unit>>,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        items.forEachIndexed { index, (label, onClick) ->
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color =
                    if (index == items.lastIndex) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.primary
                    },
                modifier = Modifier.clickable(enabled = index != items.lastIndex, onClick = onClick),
            )
            if (index != items.lastIndex) {
                Text(
                    text = " / ",
                    style = MaterialTheme.typography.labelMedium,
                    color = with(Colors) { MaterialTheme.colorScheme.separator },
                    modifier = Modifier.padding(horizontal = theme.spacing.xxs),
                )
            }
        }
    }
}
