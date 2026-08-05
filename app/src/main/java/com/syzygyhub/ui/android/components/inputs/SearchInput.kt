package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import kotlinx.coroutines.delay

private val MinTouchTarget = 48.dp

/**
 * A search field with a leading icon, trailing clear button, and built-in
 * debounce. Named `SearchInput` (not `SearchBar`) to avoid colliding with
 * `androidx.compose.material3.SearchBar`.
 */
@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
    debounceMillis: Long = 300L,
    onSearchTextChanged: ((String) -> Unit)? = null,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    if (onSearchTextChanged != null) {
        LaunchedEffect(value) {
            delay(debounceMillis)
            onSearchTextChanged(value)
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier =
            modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = MinTouchTarget)
                .semantics { contentDescription = placeholder },
        singleLine = true,
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(
                    onClick = { onValueChange("") },
                    modifier = Modifier.size(MinTouchTarget),
                ) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear search")
                }
            }
        },
    )
}
