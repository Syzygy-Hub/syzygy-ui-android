package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * A small opinionated wrapper demonstrating `LazyColumn`'s native `stickyHeader { }`
 * scope function, rather than reimplementing sticky-header behavior manually.
 */
@Composable
fun StickyHeader(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    LazyColumn(modifier = modifier) {
        stickyHeader { header() }
        item { content() }
    }
}
