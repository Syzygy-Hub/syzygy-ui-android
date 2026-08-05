package com.syzygyhub.ui.android.components.buttons

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

private val MinTouchTarget = 48.dp

@Composable
fun GhostButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String = text,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    TextButton(
        onClick = onClick,
        modifier =
            modifier
                .defaultMinSize(minHeight = MinTouchTarget)
                .semantics { this.contentDescription = contentDescription },
        enabled = enabled,
        shape = RoundedCornerShape(theme.radius.md),
    ) {
        Text(text = text)
    }
}
