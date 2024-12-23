package com.lwg.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.lwg.main.component.MainBottomItem
import com.lwg.navigation.Route

internal class MainNavigator(
    val navController: NavController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainBottomItem.HOME.route

    val currentTab: MainBottomItem?
        @Composable get() = MainBottomItem.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(bottomItem: MainBottomItem) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        // TODO 각 화면 만들고 navigate 함수 붙히기
//        when (bottomItem) {
//            MainBottomItem.HOME -> navController.navigateHome(navOptions)
//            MainBottomItem.FAVORITE -> navController.navigateSetting(navOptions)
//            MainBottomItem.CALENDAR -> navController.navigateBookmark(navOptions)
//        }
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    @Composable
    fun shouldShowBottomBar() = MainBottomItem.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}