package com.syzygyhub.ui.android.components.feedback

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** A determinate linear progress indicator, wrapping [LinearProgressIndicator]. */
@Composable
fun ProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
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
