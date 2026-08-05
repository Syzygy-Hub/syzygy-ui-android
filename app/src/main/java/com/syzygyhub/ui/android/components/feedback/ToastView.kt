package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

enum class ToastVariant { SUCCESS, WARNING, ERROR }

@Composable
fun ToastView(
    message: String,
    variant: ToastVariant,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val (containerColor, contentColor) =
        when (variant) {
            ToastVariant.SUCCESS -> Color(0xFF2E7D32) to Color.White
            ToastVariant.WARNING -> Color(0xFFF9A825) to Color.Black
            ToastVariant.ERROR -> MaterialTheme.colorScheme.error to MaterialTheme.colorScheme.onError
        }

    Text(
        text = message,
        color = contentColor,
        style = MaterialTheme.typography.bodyMedium,
        modifier =
            modifier
                .fillMaxWidth()
                .background(color = containerColor, shape = RoundedCornerShape(theme.radius.md))
                .padding(theme.spacing.md)
                .semantics { contentDescription = "${variant.name.lowercase()} toast: $message" },
    )
}
