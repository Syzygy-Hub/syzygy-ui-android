package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** Which side of a [Timeline] entry's dot/icon its content is anchored to. */
enum class TimelineItemAlignment { LEADING, TRAILING }

/** A single event within a [Timeline]. */
data class TimelineItem(
    val title: String,
    val subtitle: String? = null,
    val timestamp: String? = null,
    val icon: ImageVector? = null,
    val dotColor: Color? = null,
)

private val DotSize = 12.dp
private val IconDotSize = 24.dp
private val LineSegmentHeight = 24.dp

/**
 * A vertical list of events, each with a dot/icon on a connecting vertical
 * line, a title, optional subtitle, and optional timestamp. Also known as
 * ActivityFeed. The connecting line is drawn as fixed-height spacer segments
 * between dots rather than a precisely-measured `Canvas` path — simpler, and
 * sufficient since rows are already uniformly spaced by [Spacing.sm].
 */
@Composable
fun Timeline(
    items: List<TimelineItem>,
    modifier: Modifier = Modifier,
    alignment: TimelineItemAlignment = TimelineItemAlignment.LEADING,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            Row(
                horizontalArrangement =
                    if (alignment == TimelineItemAlignment.LEADING) Arrangement.Start else Arrangement.End,
                modifier = Modifier.fillMaxWidth(),
            ) {
                val dotAndContent =
                    @Composable {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val dotColor = item.dotColor ?: MaterialTheme.colorScheme.primary
                            if (item.icon != null) {
                                Box(
                                    modifier =
                                        Modifier
                                            .size(IconDotSize)
                                            .clip(CircleShape)
                                            .background(dotColor),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(theme.spacing.md),
                                    )
                                }
                            } else {
                                Box(
                                    modifier =
                                        Modifier
                                            .size(DotSize)
                                            .clip(CircleShape)
                                            .background(dotColor),
                                )
                            }
                            if (index != items.lastIndex) {
                                Spacer(
                                    modifier =
                                        Modifier
                                            .width(2.dp)
                                            .height(LineSegmentHeight)
                                            .background(MaterialTheme.colorScheme.outlineVariant),
                                )
                            }
                        }
                    }
                val textContent =
                    @Composable {
                        Column(modifier = Modifier.padding(bottom = theme.spacing.md)) {
                            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
                            if (item.subtitle != null) {
                                Text(text = item.subtitle, style = MaterialTheme.typography.bodyMedium)
                            }
                            if (item.timestamp != null) {
                                Text(
                                    text = item.timestamp,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }

                if (alignment == TimelineItemAlignment.LEADING) {
                    dotAndContent()
                    Spacer(modifier = Modifier.width(theme.spacing.sm))
                    textContent()
                } else {
                    textContent()
                    Spacer(modifier = Modifier.width(theme.spacing.sm))
                    dotAndContent()
                }
            }
        }
    }
}
