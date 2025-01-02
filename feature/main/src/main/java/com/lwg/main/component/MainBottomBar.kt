package com.lwg.main.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.main.MainNavigator

@Composable
internal fun MainBottomBar(
    navigator: MainNavigator
) {
    val bottomBarItems = listOf(MainBottomItem.HOME, MainBottomItem.FAVORITE, MainBottomItem.CALENDAR)

    NavigationBar {
        bottomBarItems.forEach { item ->
            NavigationBarItem(
                selected = item == navigator.currentTab,
                onClick = { navigator.navigate(item) },
                icon = { BottomIcon(item.iconResId) },
                label = {
                    Text(
                        text = item.title
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                )
            )
        }
    }
}

@Composable
private fun BottomIcon(
    iconId: Int
) {
    Icon(
        painter = painterResource(iconId),
        contentDescription = ""
    )
}

@Composable
@Preview(showBackground = true)
private fun MainBottomBarPreview() {
    LwgTheme {
        MainBottomBar(
            navigator = MainNavigator(
                rememberNavController()
            )
        )
    }
}