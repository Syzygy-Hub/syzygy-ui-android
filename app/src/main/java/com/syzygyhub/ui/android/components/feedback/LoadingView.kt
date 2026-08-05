package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    message: String? = null,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(theme.spacing.lg)
                .semantics { contentDescription = message ?: "Loading" },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(theme.spacing.md),
    ) {
        CircularProgressIndicator()
        if (message != null) {
            Text(text = message, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
