package com.syzygyhub.ui.android.components.display

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

private val MinTouchTarget = 48.dp

/**
 * A dedicated interactive counterpart to [StarRatingView]: always-tappable stars that
 * report changes via [onRatingChange], for callers who want interactivity as the default
 * rather than opting into it through `StarRatingView`'s optional `onRatingChanged`.
 */
@Composable
fun RatingInput(
    rating: Int,
    onRatingChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
) {
    Row(modifier = modifier.semantics { contentDescription = "Rating: $rating out of $maxRating stars" }) {
        for (star in 1..maxRating) {
            val filled = star <= rating
            IconButton(
                onClick = { onRatingChange(star) },
                modifier = Modifier.size(MinTouchTarget),
            ) {
                Icon(
                    imageVector = if (filled) Icons.Filled.Star else Icons.Filled.StarBorder,
                    contentDescription = "$star star${if (star == 1) "" else "s"}",
                )
            }
        }
    }
}
