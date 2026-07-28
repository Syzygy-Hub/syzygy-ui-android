package com.aks.android_ui_library.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aks.android_ui_library.components.feedback.EmptyStateView
import com.aks.android_ui_library.components.feedback.LoadingView
import com.aks.android_ui_library.components.feedback.ToastVariant
import com.aks.android_ui_library.components.feedback.ToastView
import com.aks.android_ui_library.tokens.Spacing
import com.aks.android_ui_library.ui.theme.AndroiduilibraryTheme

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FeedbackPreviews() {
    AndroiduilibraryTheme {
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
            ToastView(message = "Saved successfully", variant = ToastVariant.SUCCESS)
            ToastView(message = "Check your connection", variant = ToastVariant.WARNING)
            ToastView(message = "Something went wrong", variant = ToastVariant.ERROR)
        }
    }
}
