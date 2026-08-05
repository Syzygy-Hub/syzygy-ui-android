package com.syzygyhub.ui.android.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class SyzygyTypography(
    val largeTitle: TextStyle,
    val display: TextStyle,
    val title: TextStyle,
    val headline: TextStyle,
    val body: TextStyle,
    val callout: TextStyle,
    val subheadline: TextStyle,
    val footnote: TextStyle,
    val caption: TextStyle,
) {
    companion object {
        val default =
            SyzygyTypography(
                largeTitle = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.Bold),
                display = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                title = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
                headline = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.SemiBold),
                body = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Normal),
                callout = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
                subheadline = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal),
                footnote = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal),
                caption = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
            )
        val highContrast =
            SyzygyTypography(
                largeTitle = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.Black),
                display = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Black),
                title = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                headline = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
                body = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.SemiBold),
                callout = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
                subheadline = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
                footnote = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Medium),
                caption = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
            )
    }
}
