package com.aks.android_ui_library.previews

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aks.android_ui_library.components.cards.CardView
import com.aks.android_ui_library.tokens.Spacing
import com.aks.android_ui_library.ui.theme.AndroiduilibraryTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardPreviews() {
    AndroiduilibraryTheme {
        CardView(modifier = Modifier.padding(Spacing.md)) {
            Text(text = "Card title", style = MaterialTheme.typography.titleMedium)
            Text(text = "Supporting card content goes here.", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
