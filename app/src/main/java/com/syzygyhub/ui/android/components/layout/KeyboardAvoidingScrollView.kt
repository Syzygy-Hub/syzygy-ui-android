package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A scrollable column that automatically insets its content to avoid the
 * on-screen keyboard, for forms built outside of a `LazyColumn` (which
 * already handles this natively). Wraps [Modifier.imePadding].
 */
@Composable
fun KeyboardAvoidingScrollView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier =
            modifier
                .verticalScroll(rememberScrollState())
                .imePadding(),
    ) {
        content()
    }
}
