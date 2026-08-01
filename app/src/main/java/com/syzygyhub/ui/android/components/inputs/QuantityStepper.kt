package com.syzygyhub.ui.android.components.inputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

private val MinTouchTarget = 48.dp

/** A +/- quantity control, bounded to [range] and incrementing by [step]. */
@Composable
fun QuantityStepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    range: IntRange = 0..99,
    step: Int = 1,
) {
    Row(
        modifier = modifier.semantics { contentDescription = "Quantity: $value" },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onValueChange((value - step).coerceAtLeast(range.first)) },
            enabled = value > range.first,
            modifier = Modifier.size(MinTouchTarget),
        ) {
            Icon(imageVector = Icons.Filled.Remove, contentDescription = "Decrease")
        }
        Text(text = "$value")
        IconButton(
            onClick = { onValueChange((value + step).coerceAtMost(range.last)) },
            enabled = value < range.last,
            modifier = Modifier.size(MinTouchTarget),
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Increase")
        }
    }
}
