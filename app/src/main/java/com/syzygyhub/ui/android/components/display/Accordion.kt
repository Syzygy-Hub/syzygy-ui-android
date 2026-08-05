package com.syzygyhub.ui.android.components.display

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

private val MinTouchTarget = 48.dp

/** A single titled, expandable section within an [Accordion]. */
data class AccordionSection(
    val title: String,
    val content: @Composable () -> Unit,
)

/**
 * A vertically stacked list of expandable/collapsible sections, each with a
 * tappable header — mirrors [com.syzygyhub.ui.android.components.overlay.CollapsibleView]'s
 * expand/collapse animation for each individual section, but coordinates
 * open/closed state across the whole group. By default only one section is
 * open at a time; pass `allowsMultipleOpen = true` to let several sections
 * stay open simultaneously.
 */
@Composable
fun Accordion(
    sections: List<AccordionSection>,
    modifier: Modifier = Modifier,
    allowsMultipleOpen: Boolean = false,
    initiallyOpenIndices: Set<Int> = emptySet(),
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    var openIndices by remember { mutableStateOf(initiallyOpenIndices) }

    Column(modifier = modifier) {
        sections.forEachIndexed { index, section ->
            val isExpanded = index in openIndices
            val rotation by animateFloatAsState(
                targetValue = if (isExpanded) 180f else 0f,
                label = "accordionChevronRotation",
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = MinTouchTarget)
                        .clickable {
                            openIndices =
                                when {
                                    isExpanded -> openIndices - index
                                    allowsMultipleOpen -> openIndices + index
                                    else -> setOf(index)
                                }
                        }.padding(horizontal = theme.spacing.md)
                        .semantics {
                            contentDescription = "${section.title}, ${if (isExpanded) "expanded" else "collapsed"}"
                        },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = section.title, style = MaterialTheme.typography.titleMedium)
                Icon(
                    imageVector = Icons.Filled.ExpandMore,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation),
                )
            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut(),
            ) {
                Column(modifier = Modifier.padding(horizontal = theme.spacing.md, vertical = theme.spacing.sm)) {
                    section.content()
                }
            }
        }
    }
}
