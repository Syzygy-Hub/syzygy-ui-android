package com.syzygyhub.ui.android.components.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.syzygyhub.ui.android.tokens.Elevation
import com.syzygyhub.ui.android.tokens.Radius
import com.syzygyhub.ui.android.tokens.Spacing

/**
 * An anchored floating content bubble, typically triggered by a tap on the caller's
 * anchor composable. Compose has no true native popover primitive (unlike SwiftUI), so
 * this wraps [Popup] and styles it with this library's surface/radius/elevation tokens.
 */
@Composable
fun Popover(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: IntOffset = IntOffset(0, 0),
    content: @Composable () -> Unit,
) {
    if (isVisible) {
        Popup(
            offset = offset,
            onDismissRequest = onDismissRequest,
            properties = PopupProperties(focusable = true),
        ) {
            Surface(
                modifier = modifier.clip(RoundedCornerShape(Radius.md)),
                shape = RoundedCornerShape(Radius.md),
                tonalElevation = Elevation.md,
                shadowElevation = Elevation.md,
                color = MaterialTheme.colorScheme.surface,
            ) {
                Box(modifier = Modifier.padding(Spacing.md)) {
                    content()
                }
            }
        }
    }
}
