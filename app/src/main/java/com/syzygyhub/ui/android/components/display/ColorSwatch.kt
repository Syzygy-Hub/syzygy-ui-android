package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.tokens.Radius
import com.syzygyhub.ui.android.tokens.Spacing

/** The outline shape a [ColorSwatch] renders as. */
enum class ColorSwatchShape { CIRCLE, SQUARE }

private val SelectionBorderWidth = 2.dp

/**
 * A circle or square swatch displaying a single [color], with an optional
 * [label] beneath and a selection border (drawn in the `focus`/`primary`
 * color token) shown when [isSelected] is true.
 */
@Composable
fun ColorSwatch(
    color: Color,
    modifier: Modifier = Modifier,
    label: String? = null,
    shape: ColorSwatchShape = ColorSwatchShape.CIRCLE,
    isSelected: Boolean = false,
    size: Dp = 32.dp,
) {
    val clipShape: Shape = if (shape == ColorSwatchShape.CIRCLE) CircleShape else RoundedCornerShape(Radius.sm)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.semantics { contentDescription = label ?: "Color swatch" },
    ) {
        Box(
            modifier =
                Modifier
                    .size(size)
                    .clip(clipShape)
                    .background(color)
                    .then(
                        if (isSelected) {
                            Modifier.border(SelectionBorderWidth, MaterialTheme.colorScheme.primary, clipShape)
                        } else {
                            Modifier
                        },
                    ),
        )
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = Spacing.xxs),
            )
        }
    }
}
