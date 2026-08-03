package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.components.feedback.AppSnackbar
import com.syzygyhub.ui.android.components.feedback.CircularProgress
import com.syzygyhub.ui.android.components.feedback.EmptyStateView
import com.syzygyhub.ui.android.components.feedback.ErrorStateView
import com.syzygyhub.ui.android.components.feedback.InlineAlert
import com.syzygyhub.ui.android.components.feedback.InlineAlertVariant
import com.syzygyhub.ui.android.components.feedback.LoadingView
import com.syzygyhub.ui.android.components.feedback.ProgressBar
import com.syzygyhub.ui.android.components.feedback.ShimmerView
import com.syzygyhub.ui.android.components.feedback.SkeletonShape
import com.syzygyhub.ui.android.components.feedback.SkeletonView
import com.syzygyhub.ui.android.components.feedback.ToastVariant
import com.syzygyhub.ui.android.components.feedback.ToastView
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FeedbackPreviews() {
    SyzygyUiTheme {
        Column(
            modifier = Modifier.padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.md),
        ) {
            LoadingView(message = "Loading…")
            EmptyStateView(
                icon = Icons.Filled.Search,
                title = "No results",
                subtitle = "Try adjusting your search.",
                ctaLabel = "Reset",
                onCtaClick = {},
            )
            ErrorStateView(
                title = "Something went wrong",
                subtitle = "We couldn't load this page.",
                onRetryClick = {},
            )
            ToastView(message = "Saved successfully", variant = ToastVariant.SUCCESS)
            ToastView(message = "Check your connection", variant = ToastVariant.WARNING)
            ToastView(message = "Something went wrong", variant = ToastVariant.ERROR)
            ShimmerView()
            ProgressBar(progress = 0.65f)
            SkeletonView(width = 120.dp, height = 16.dp)
            SkeletonView(width = 48.dp, height = 48.dp, shape = SkeletonShape.CIRCLE)
            CircularProgress(progress = 0.4f)
            CircularProgress(progress = null)
            InlineAlert(message = "Your changes were saved.", variant = InlineAlertVariant.SUCCESS)
            InlineAlert(message = "This action can't be undone.", variant = InlineAlertVariant.ERROR)
            AppSnackbar(message = "Copied to clipboard", isVisible = true, onDismiss = {})
        }
    }
}
