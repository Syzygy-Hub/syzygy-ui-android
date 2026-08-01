package com.syzygyhub.ui.android.components.display

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics

/**
 * A hairline horizontal rule. Named `DividerLine` (not `Divider`) to avoid
 * ambiguity with `androidx.compose.material3.HorizontalDivider`.
 */
@Composable
fun DividerLine(modifier: Modifier = Modifier) {
    HorizontalDivider(modifier = modifier.semantics { hideFromAccessibility() })
}
