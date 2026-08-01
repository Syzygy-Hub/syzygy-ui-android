package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.Spacing

/**
 * A small numeric/dot badge meant to overlay an icon (e.g. a bell with an
 * unread count). Distinct from the repo's own `Badge`, which is a
 * standalone labeled pill.
 */
@Composable
fun CountBadge(
    modifier: Modifier = Modifier,
    count: Int? = null,
    maxDisplayCount: Int = 99,
) {
    if (count != null && count > 0) {
        val display = if (count > maxDisplayCount) "$maxDisplayCount+" else "$count"
        Box(
            modifier =
                modifier
                    .defaultMinSize(minWidth = 16.dp, minHeight = 16.dp)
                    .background(MaterialTheme.colorScheme.error, CircleShape)
                    .padding(horizontal = Spacing.xs)
                    .semantics { contentDescription = "$count unread" },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = display,
                color = MaterialTheme.colorScheme.onError,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    } else {
        Box(
            modifier =
                modifier
                    .size(10.dp)
                    .background(MaterialTheme.colorScheme.error, CircleShape)
                    .semantics { contentDescription = "New" },
        )
    }
}
