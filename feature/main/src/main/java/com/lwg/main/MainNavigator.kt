package com.lwg.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.lwg.calendar.navigation.navigateToCalendar
import com.lwg.favorite.navigation.navigateToFavorite
import com.lwg.home.navigation.navigateToHome
import com.lwg.main.component.MainBottomItem
import com.lwg.navigation.MainBottomBarRoute
import com.lwg.navigation.Route
import com.lwg.util.Logger
import coml.lwg.movie_detail.navigation.navigateToMovieDetail

internal class MainNavigator(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() {
            val backStackEntry by navController.currentBackStackEntryAsState()
            return remember(backStackEntry) { backStackEntry?.destination }
        }

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

        when (bottomItem) {
            MainBottomItem.HOME -> navController.navigateToHome(navOptions)
            MainBottomItem.FAVORITE -> navController.navigateToFavorite(navOptions)
            MainBottomItem.CALENDAR -> navController.navigateToCalendar(navOptions)
        }
    }

    fun navigateToMovieDetail(
        movieId: Int
    ) {
        navController.navigateToMovieDetail(movieId)
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<MainBottomBarRoute.Home>()) {
            popBackStack()
        }
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        val destination by rememberUpdatedState(newValue = currentDestination) // ✅ 최신 값 유지

        val showBottomBar by remember(destination) { // ✅ destination 변경 시 업데이트
            mutableStateOf(MainBottomItem.contains {
                destination?.hasRoute(it::class) == true
            })
        }

        return showBottomBar
    }
}