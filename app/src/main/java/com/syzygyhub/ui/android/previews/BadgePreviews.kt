package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syzygyhub.ui.android.components.badges.Badge
import com.syzygyhub.ui.android.components.badges.BadgeVariant
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BadgePreviews() {
    SyzygyUiTheme {
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
