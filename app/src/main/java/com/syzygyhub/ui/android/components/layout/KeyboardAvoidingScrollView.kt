package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/**
 * A scrollable column that automatically insets its content to avoid the
 * on-screen keyboard, for forms built outside of a `LazyColumn` (which
 * already handles this natively). Wraps [Modifier.imePadding].
 */
@Composable
fun KeyboardAvoidingScrollView(
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Column(
        modifier =
            modifier
                .verticalScroll(rememberScrollState())
                .imePadding(),
    ) {
        content()
    }
}
