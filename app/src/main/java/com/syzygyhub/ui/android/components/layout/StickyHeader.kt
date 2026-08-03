package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A small opinionated wrapper demonstrating `LazyColumn`'s native `stickyHeader { }`
 * scope function, rather than reimplementing sticky-header behavior manually.
 */
@Composable
fun StickyHeader(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    LazyColumn(modifier = modifier) {
        stickyHeader { header() }
        item { content() }
    }
}
