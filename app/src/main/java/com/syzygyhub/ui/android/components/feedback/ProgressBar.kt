package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics

/** A determinate linear progress indicator, wrapping [LinearProgressIndicator]. */
@Composable
fun ProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val clamped = progress.coerceIn(0f, 1f)
    LinearProgressIndicator(
        progress = { clamped },
        modifier =
            modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "Progress"
                    progressBarRangeInfo = ProgressBarRangeInfo(clamped, 0f..1f)
                },
    )
}
