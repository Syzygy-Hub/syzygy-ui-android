package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class AvatarSize(val dimension: Dp) {
    SMALL(32.dp),
    MEDIUM(44.dp),
    LARGE(64.dp),
}

/** A circular avatar showing a fallback initials label. */
@Composable
fun Avatar(
    initials: String,
    modifier: Modifier = Modifier,
    size: AvatarSize = AvatarSize.MEDIUM,
) {
    Box(
        modifier =
            modifier
                .size(size.dimension)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .semantics { contentDescription = initials },
        contentAlignment = Alignment.Center,
    ) {
        Text(text = initials, color = MaterialTheme.colorScheme.onPrimary)
    }
}
