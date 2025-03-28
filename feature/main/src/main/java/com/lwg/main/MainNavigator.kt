package com.lwg.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import coml.lwg.movie_detail.navigation.navigateToMovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MainNavigator(
    val navController: NavHostController
) {
    private val _currentTab = MutableStateFlow<MainBottomItem?>(null)
    val currentTab: StateFlow<MainBottomItem?> = _currentTab.asStateFlow()

    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = MainBottomItem.HOME.route

    init {
        // BackStackEntry 변경 감지하여 currentTab 갱신
        navController.addOnDestinationChangedListener { _, destination, _ ->
            _currentTab.value = MainBottomItem.find { tab ->
                destination.hasRoute(tab::class)
            }
        }
    }

    fun navigate(bottomItem: MainBottomItem) {
        if (bottomItem == currentTab.value) return

        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }
            launchSingleTop = true
            restoreState = false
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