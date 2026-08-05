package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.components.display.DividerLine
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** Where a [LabeledDivider]'s label sits along the line. */
enum class LabeledDividerAlignment { LEADING, CENTER, TRAILING }

private val LeadingLineWidth = 24.dp

/**
 * A horizontal [DividerLine] broken by a centered (or leading/trailing) text
 * label — built as a [Row] of two weighted [DividerLine] segments flanking
 * the [label] [androidx.compose.material3.Text].
 */
@Composable
fun LabeledDivider(
    label: String,
    modifier: Modifier = Modifier,
    alignment: LabeledDividerAlignment = LabeledDividerAlignment.CENTER,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (alignment) {
            LabeledDividerAlignment.LEADING -> {
                DividerLine(modifier = Modifier.width(LeadingLineWidth))
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = theme.spacing.sm)
                )
                DividerLine(modifier = Modifier.weight(1f))
            }
            LabeledDividerAlignment.CENTER -> {
                DividerLine(modifier = Modifier.weight(1f))
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = theme.spacing.sm)
                )
                DividerLine(modifier = Modifier.weight(1f))
            }
            LabeledDividerAlignment.TRAILING -> {
                DividerLine(modifier = Modifier.weight(1f))
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = theme.spacing.sm)
                )
                DividerLine(modifier = Modifier.width(LeadingLineWidth))
            }
        }
    }
}
