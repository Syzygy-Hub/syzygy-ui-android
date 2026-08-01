package com.syzygyhub.ui.android.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material3.IconButton as Material3IconButton

private val MinTouchTarget = 48.dp

@Composable
fun IconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Material3IconButton(
        onClick = onClick,
        modifier = modifier.size(MinTouchTarget),
        enabled = enabled,
        colors = IconButtonDefaults.iconButtonColors(),
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}
