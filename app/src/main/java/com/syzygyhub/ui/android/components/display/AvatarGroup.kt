package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** An overlapping stack of [Avatar]s, showing up to [max] with a "+N" overflow badge. */
@Composable
fun AvatarGroup(
    avatars: List<String>,
    modifier: Modifier = Modifier,
    max: Int = 4,
    size: AvatarSize = AvatarSize.MEDIUM,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val visible = avatars.take(max)
    val overflow = avatars.size - visible.size
    val overlap = size.dimension / 3

    Row(modifier = modifier) {
        visible.forEachIndexed { index, initials ->
            Avatar(
                initials = initials,
                size = size,
                modifier = Modifier.offset(x = -overlap * index),
            )
        }
        if (overflow > 0) {
            Box(
                modifier =
                    Modifier
                        .offset(x = -overlap * visible.size)
                        .size(size.dimension)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "+$overflow", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
