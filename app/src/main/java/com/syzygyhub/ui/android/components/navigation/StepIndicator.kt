package com.syzygyhub.ui.android.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.BorderWidth

/**
 * A horizontal step-progress indicator (also known as WizardSteps): a filled circle
 * for completed steps, a ring for the active step, and a dot for pending steps,
 * connected by lines.
 */
@Composable
fun StepIndicator(
    steps: List<String>,
    currentStep: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        steps.forEachIndexed { index, _ ->
            val completed = index < currentStep
            val active = index == currentStep

            Box(
                modifier =
                    Modifier
                        .size(if (active) 16.dp else 12.dp)
                        .clip(CircleShape)
                        .then(
                            when {
                                completed ->
                                    Modifier.background(MaterialTheme.colorScheme.primary)
                                active ->
                                    Modifier.border(
                                        BorderWidth.thick,
                                        MaterialTheme.colorScheme.primary,
                                        CircleShape,
                                    )
                                else ->
                                    Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
                            },
                        ),
            )

            if (index != steps.lastIndex) {
                Box(
                    modifier =
                        Modifier
                            .weight(1f)
                            .height(BorderWidth.thick)
                            .background(
                                if (completed) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.surfaceVariant
                                },
                            ),
                )
            }
        }
    }
}
