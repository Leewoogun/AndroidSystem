package com.lwg.main

import androidx.compose.runtime.Composable
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
import coml.lwg.movie_detail.navigation.navigateToMovieDetail

internal class MainNavigator(
    val navController: NavHostController
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
    fun shouldShowBottomBar() = MainBottomItem.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}