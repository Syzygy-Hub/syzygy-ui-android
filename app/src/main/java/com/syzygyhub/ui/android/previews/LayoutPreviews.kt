package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.components.display.Chip
import com.syzygyhub.ui.android.components.inputs.SecureInput
import com.syzygyhub.ui.android.components.inputs.TextInput
import com.syzygyhub.ui.android.components.layout.AdaptiveStack
import com.syzygyhub.ui.android.components.layout.FlowLayout
import com.syzygyhub.ui.android.components.layout.KeyboardAvoidingScrollView
import com.syzygyhub.ui.android.components.layout.StickyHeader
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LayoutPreviews() {
    SyzygyUiTheme {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        KeyboardAvoidingScrollView {
            Column(
                modifier = Modifier.padding(Spacing.md),
                verticalArrangement = Arrangement.spacedBy(Spacing.sm),
            ) {
                TextInput(label = "Email", value = email, onValueChange = { email = it })
                SecureInput(label = "Password", value = password, onValueChange = { password = it })
                AdaptiveStack(breakpoint = 600.dp) {
                    Text("Left")
                    Text("Right")
                }
                FlowLayout {
                    Chip(text = "Kotlin")
                    Chip(text = "Compose")
                    Chip(text = "Android")
                }
                StickyHeader(
                    header = { Text("Section", style = MaterialTheme.typography.titleMedium) },
                    content = { Text("Body content") },
                )
            }
        }
    }
}
