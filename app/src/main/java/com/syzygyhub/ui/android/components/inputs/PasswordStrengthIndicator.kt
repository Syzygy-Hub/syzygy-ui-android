package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.AppTypography.caption
import com.syzygyhub.ui.android.tokens.Radius
import com.syzygyhub.ui.android.tokens.Spacing

enum class PasswordStrength(val label: String) {
    WEAK("Weak"),
    FAIR("Fair"),
    STRONG("Strong"),
    VERY_STRONG("Very Strong"),
}

/** Computes a strength heuristic from length + character-class variety (not a hardcoded value). */
fun computePasswordStrength(password: String): PasswordStrength {
    if (password.isEmpty()) return PasswordStrength.WEAK

    var classCount = 0
    if (password.any { it.isLowerCase() }) classCount++
    if (password.any { it.isUpperCase() }) classCount++
    if (password.any { it.isDigit() }) classCount++
    if (password.any { !it.isLetterOrDigit() }) classCount++

    val lengthScore =
        when {
            password.length >= 12 -> 3
            password.length >= 8 -> 2
            password.length >= 5 -> 1
            else -> 0
        }
    val score = lengthScore + classCount

    return when {
        score <= 2 -> PasswordStrength.WEAK
        score <= 4 -> PasswordStrength.FAIR
        score <= 6 -> PasswordStrength.STRONG
        else -> PasswordStrength.VERY_STRONG
    }
}

/** A segmented strength bar + text label, computed live from [password] via [computePasswordStrength]. */
@Composable
fun PasswordStrengthIndicator(
    password: String,
    modifier: Modifier = Modifier,
) {
    val strength = computePasswordStrength(password)
    val filledSegments =
        when (strength) {
            PasswordStrength.WEAK -> 1
            PasswordStrength.FAIR -> 2
            PasswordStrength.STRONG -> 3
            PasswordStrength.VERY_STRONG -> 4
        }
    val activeColor =
        when (strength) {
            PasswordStrength.WEAK -> MaterialTheme.colorScheme.error
            PasswordStrength.FAIR -> MaterialTheme.colorScheme.tertiary
            PasswordStrength.STRONG -> MaterialTheme.colorScheme.primary
            PasswordStrength.VERY_STRONG -> MaterialTheme.colorScheme.primary
        }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs),
        ) {
            for (i in 0 until 4) {
                Row(
                    modifier =
                        Modifier
                            .weight(1f)
                            .height(4.dp)
                            .clip(RoundedCornerShape(Radius.xs))
                            .background(
                                if (i < filledSegments) activeColor else MaterialTheme.colorScheme.surfaceVariant,
                            ),
                ) {}
            }
        }
        Text(
            text = strength.label,
            style = MaterialTheme.typography.caption,
            color = activeColor,
        )
    }
}
