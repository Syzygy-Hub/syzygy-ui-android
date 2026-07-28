package com.aks.android_ui_library.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aks.android_ui_library.components.buttons.DestructiveButton
import com.aks.android_ui_library.components.buttons.GhostButton
import com.aks.android_ui_library.components.buttons.IconButton
import com.aks.android_ui_library.components.buttons.PrimaryButton
import com.aks.android_ui_library.components.buttons.SecondaryButton
import com.aks.android_ui_library.tokens.Spacing
import com.aks.android_ui_library.ui.theme.AndroiduilibraryTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ButtonPreviews() {
    AndroiduilibraryTheme {
        Column(
            modifier = Modifier.padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            PrimaryButton(text = "Primary", onClick = {})
            SecondaryButton(text = "Secondary", onClick = {})
            DestructiveButton(text = "Destructive", onClick = {})
            GhostButton(text = "Ghost", onClick = {})
            IconButton(icon = Icons.Filled.Favorite, contentDescription = "Favorite", onClick = {})
        }
    }
}
