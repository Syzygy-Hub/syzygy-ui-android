package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syzygyhub.ui.android.components.buttons.DestructiveButton
import com.syzygyhub.ui.android.components.buttons.GhostButton
import com.syzygyhub.ui.android.components.buttons.IconButton
import com.syzygyhub.ui.android.components.buttons.PrimaryButton
import com.syzygyhub.ui.android.components.buttons.SecondaryButton
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ButtonPreviews() {
    SyzygyUiTheme {
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
