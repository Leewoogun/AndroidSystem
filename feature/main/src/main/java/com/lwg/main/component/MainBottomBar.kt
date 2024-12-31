package com.lwg.main.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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
                }
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
    MainBottomBar(
        navigator = MainNavigator(
            rememberNavController()
        )
    )
}