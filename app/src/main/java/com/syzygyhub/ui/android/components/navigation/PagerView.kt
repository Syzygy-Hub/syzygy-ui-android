package com.syzygyhub.ui.android.components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * Swipeable, paged content — e.g. onboarding screens or an image carousel.
 * Distinct from [TabBar], which is navigation chrome; this has no chrome of
 * its own, just paged content driven by a [PagerState].
 */
@Composable
fun PagerView(
    state: PagerState,
    modifier: Modifier = Modifier,
    pageContent: @Composable (page: Int) -> Unit,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    HorizontalPager(
        state = state,
        modifier = modifier.fillMaxSize(),
    ) { page ->
        pageContent(page)
    }
}
