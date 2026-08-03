package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syzygyhub.ui.android.components.overlay.CollapsibleView
import com.syzygyhub.ui.android.components.overlay.Popover
import com.syzygyhub.ui.android.components.overlay.Tooltip
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OverlayPreviews() {
    SyzygyUiTheme {
        Column(modifier = Modifier.padding(Spacing.md)) {
            CollapsibleView(
                title = "Shipping details",
                initiallyExpanded = true,
            ) {
                Text(
                    text = "Delivered in 3-5 business days.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Tooltip(text = "More info") {
                Text("Hover or long-press me")
            }
            Popover(isVisible = true, onDismissRequest = {}) {
                Text("Popover content")
            }
        }
    }
}
