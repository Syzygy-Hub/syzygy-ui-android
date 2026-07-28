package com.aks.android_ui_library.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aks.android_ui_library.components.badges.Badge
import com.aks.android_ui_library.components.badges.BadgeVariant
import com.aks.android_ui_library.tokens.Spacing
import com.aks.android_ui_library.ui.theme.AndroiduilibraryTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BadgePreviews() {
    AndroiduilibraryTheme {
        Row(
            modifier = Modifier.padding(Spacing.md),
            horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            Badge(text = "New", variant = BadgeVariant.PRIMARY)
            Badge(text = "Active", variant = BadgeVariant.SUCCESS)
            Badge(text = "Pending", variant = BadgeVariant.WARNING)
            Badge(text = "Failed", variant = BadgeVariant.ERROR)
        }
    }
}
