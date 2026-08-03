package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.components.display.Avatar
import com.syzygyhub.ui.android.components.display.AvatarGroup
import com.syzygyhub.ui.android.components.display.AvatarSize
import com.syzygyhub.ui.android.components.display.Chip
import com.syzygyhub.ui.android.components.display.CountBadge
import com.syzygyhub.ui.android.components.display.DividerLine
import com.syzygyhub.ui.android.components.display.LazyImageView
import com.syzygyhub.ui.android.components.display.ListRow
import com.syzygyhub.ui.android.components.display.RatingInput
import com.syzygyhub.ui.android.components.display.SectionHeader
import com.syzygyhub.ui.android.components.display.StarRatingView
import com.syzygyhub.ui.android.components.display.StatsCard
import com.syzygyhub.ui.android.components.display.Trend
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DisplayPreviews() {
    SyzygyUiTheme {
        Column(
            modifier = Modifier.padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.md),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                Avatar(initials = "AK", size = AvatarSize.SMALL)
                Avatar(initials = "AK", size = AvatarSize.MEDIUM)
                Avatar(initials = "AK", size = AvatarSize.LARGE)
            }
            DividerLine()
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
                Chip(text = "Kotlin")
                Chip(text = "Compose", onRemove = {})
            }
            ListRow(title = "Settings", subtitle = "Manage your preferences", onClick = {})
            SectionHeader(title = "Recent Activity", actionLabel = "See All", onActionClick = {})
            StarRatingView(rating = 3)
            Row(horizontalArrangement = Arrangement.spacedBy(Spacing.lg)) {
                CountBadge(count = 3)
                CountBadge(count = 128)
                CountBadge()
            }
            LazyImageView(url = null, modifier = Modifier.size(80.dp))
            AvatarGroup(avatars = listOf("AK", "BC", "DE", "FG", "HI"), max = 3)
            StatsCard(label = "Revenue", value = "$12.4k", trend = Trend.UP, trendValue = "+12%")
            RatingInput(rating = 3, onRatingChange = {})
        }
    }
}
