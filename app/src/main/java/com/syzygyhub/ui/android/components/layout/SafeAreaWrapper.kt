package com.syzygyhub.ui.android.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme

/** Which edges of [WindowInsets.safeDrawing] a [SafeAreaWrapper] should respect. */
enum class SafeAreaEdge { TOP, BOTTOM, START, END }

private val AllSafeAreaEdges = setOf(SafeAreaEdge.TOP, SafeAreaEdge.BOTTOM, SafeAreaEdge.START, SafeAreaEdge.END)

/**
 * Applies safe-area/system-bar insets via Compose Foundation's native
 * `WindowInsets.safeDrawing` (first-party, not third-party), through
 * `Modifier.windowInsetsPadding(...)`. [edges] configures which sides to
 * respect, so a consumer can opt into only the insets they need (e.g. `TOP`
 * only, if bottom system bars are already handled elsewhere).
 *
 * On Android, correct `WindowInsets` handling is genuinely non-trivial
 * boilerplate that consumers commonly get wrong (edge-to-edge layouts,
 * display cutouts, gesture nav bars) — this component earns its keep here in
 * a way that has no real analogue on iOS, where safe-area handling is
 * mostly automatic via `safeAreaInsets`/`.ignoresSafeArea()`.
 */
@Composable
fun SafeAreaWrapper(
    modifier: Modifier = Modifier,
    edges: Set<SafeAreaEdge> = AllSafeAreaEdges,
    theme: SyzygyTheme? = null,
    content: @Composable () -> Unit,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val sides = edges.toWindowInsetsSides()
    val boxModifier =
        if (sides != null) {
            modifier.windowInsetsPadding(WindowInsets.safeDrawing.only(sides))
        } else {
            modifier
        }

    Box(modifier = boxModifier) {
        content()
    }
}

private fun Set<SafeAreaEdge>.toWindowInsetsSides(): WindowInsetsSides? {
    var result: WindowInsetsSides? = null
    if (SafeAreaEdge.TOP in this) result = WindowInsetsSides.Top
    if (SafeAreaEdge.BOTTOM in this) result = result?.plus(WindowInsetsSides.Bottom) ?: WindowInsetsSides.Bottom
    if (SafeAreaEdge.START in this) result = result?.plus(WindowInsetsSides.Start) ?: WindowInsetsSides.Start
    if (SafeAreaEdge.END in this) result = result?.plus(WindowInsetsSides.End) ?: WindowInsetsSides.End
    return result
}
