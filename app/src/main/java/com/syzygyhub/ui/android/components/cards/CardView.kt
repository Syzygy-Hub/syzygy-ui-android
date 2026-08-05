package com.syzygyhub.ui.android.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

@Composable
fun CardView(
    modifier: Modifier = Modifier,
    elevation: Dp = 2.dp,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(theme.radius.lg),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
    ) {
        Column(modifier = Modifier.padding(theme.spacing.md)) {
            content()
        }
    }
}
