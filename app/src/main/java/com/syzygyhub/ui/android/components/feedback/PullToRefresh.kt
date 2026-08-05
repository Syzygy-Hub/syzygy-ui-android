package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** A scrollable container with native pull-to-refresh wired to a refresh handler. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}
