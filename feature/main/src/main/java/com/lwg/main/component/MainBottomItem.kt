package com.lwg.main.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lwg.main.MainNavigator
import com.lwg.main.R
import com.lwg.navigation.MainBottomBarRoute
import com.lwg.navigation.Route

internal enum class MainBottomItem(
    val iconResId : Int,
    val title: String,
    val route: MainBottomBarRoute
) {
    HOME(
        iconResId = R.drawable.ic_home,
        title = "홈",
        route = MainBottomBarRoute.Home
    ),

    FAVORITE(
        iconResId = R.drawable.ic_favorite_bottom,
        title = "즐겨찾기",
        route = MainBottomBarRoute.Favorite
    ),

    CALENDAR(
        iconResId = R.drawable.ic_calendar,
        title = "캘린더",
        route = MainBottomBarRoute.Calendar
    );

    companion object {
        fun find(predicate: (MainBottomBarRoute) -> Boolean): MainBottomItem? {
            return entries.find { predicate(it.route) }
        }

        fun contains(predicate: (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}