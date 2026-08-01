package com.syzygyhub.ui.android.components.buttons

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.Radius

private val MinTouchTarget = 48.dp

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String = text,
) {
    OutlinedButton(
        onClick = onClick,
        modifier =
            modifier
                .defaultMinSize(minHeight = MinTouchTarget)
                .semantics { this.contentDescription = contentDescription },
        enabled = enabled,
        shape = RoundedCornerShape(Radius.md),
    ) {
        Text(text = text)
    }
}
