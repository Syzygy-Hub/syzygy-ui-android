package com.syzygyhub.ui.android.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** A primary button with a built-in loading spinner that replaces the label while [isLoading]. */
@Composable
fun LoadingButton(
    label: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = modifier,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = LocalContentColor.current,
                strokeWidth = 2.dp,
            )
        } else {
            Text(label)
        }
    }
}
