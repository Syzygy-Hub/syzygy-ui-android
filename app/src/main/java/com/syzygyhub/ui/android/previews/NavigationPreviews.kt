package com.syzygyhub.ui.android.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syzygyhub.ui.android.components.navigation.AppBar
import com.syzygyhub.ui.android.components.navigation.BackButton
import com.syzygyhub.ui.android.components.navigation.BottomNavigationBar
import com.syzygyhub.ui.android.components.navigation.Breadcrumbs
import com.syzygyhub.ui.android.components.navigation.FloatingTabBar
import com.syzygyhub.ui.android.components.navigation.StepIndicator
import com.syzygyhub.ui.android.components.navigation.TabBar
import com.syzygyhub.ui.android.components.navigation.TabBarItem
import com.syzygyhub.ui.android.tokens.Spacing
import com.syzygyhub.ui.android.ui.theme.SyzygyUiTheme

private val previewTabItems =
    listOf(
        TabBarItem(tag = "home", icon = Icons.Filled.Home, label = "Home"),
        TabBarItem(tag = "search", icon = Icons.Filled.Search, label = "Search"),
        TabBarItem(tag = "profile", icon = Icons.Filled.Person, label = "Profile"),
    )

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NavigationPreviews() {
    SyzygyUiTheme {
        Column {
            AppBar(title = "Settings")
            BackButton(onClick = {}, modifier = Modifier.padding(Spacing.md))
            TabBar(items = previewTabItems, selection = "home", onSelectionChange = {})
            BottomNavigationBar(
                items = previewTabItems,
                selection = "search",
                onSelectionChange = {},
                modifier = Modifier.padding(Spacing.md),
            )
            FloatingTabBar(
                items = previewTabItems,
                selection = "profile",
                onSelectionChange = {},
                modifier = Modifier.padding(Spacing.md),
            )
            StepIndicator(
                steps = listOf("Cart", "Shipping", "Payment", "Review"),
                currentStep = 1,
                modifier = Modifier.padding(Spacing.md),
            )
            Breadcrumbs(
                items = listOf("Home" to {}, "Settings" to {}, "Profile" to {}),
                modifier = Modifier.padding(Spacing.md),
            )
        }
    }
}
