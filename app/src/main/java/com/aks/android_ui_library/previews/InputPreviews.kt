package com.aks.android_ui_library.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aks.android_ui_library.components.inputs.SecureInput
import com.aks.android_ui_library.components.inputs.TextInput
import com.aks.android_ui_library.tokens.Spacing
import com.aks.android_ui_library.ui.theme.AndroiduilibraryTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InputPreviews() {
    AndroiduilibraryTheme {
        var text by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var bio by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.sm),
        ) {
            TextInput(label = "Email", value = text, onValueChange = { text = it })
            TextInput(label = "Email", value = text, onValueChange = { text = it }, errorMessage = "Invalid email")
            TextInput(label = "Bio", value = bio, onValueChange = { bio = it }, maxLength = 100)
            SecureInput(label = "Password", value = password, onValueChange = { password = it })
        }
    }
}
