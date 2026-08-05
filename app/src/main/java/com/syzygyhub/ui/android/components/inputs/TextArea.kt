package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** A multi-line text input, matching [TextInput]'s visual conventions. */
@Composable
fun TextArea(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    enabled: Boolean = true,
    minLines: Int = 3,
    maxLines: Int = 6,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier =
            modifier
                .fillMaxWidth()
                .semantics { contentDescription = label },
        enabled = enabled,
        isError = errorMessage != null,
        supportingText = errorMessage?.let { { Text(it) } },
        singleLine = false,
        minLines = minLines,
        maxLines = maxLines,
    )
}
