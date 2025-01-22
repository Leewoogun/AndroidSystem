package com.lwg.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwg.home.HomeRoute
import com.lwg.navigation.MainBottomBarRoute


fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(MainBottomBarRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    onBackEvent: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    onMovieClick: (Int) -> Unit
) {
    composable<MainBottomBarRoute.Home> {
        HomeRoute(
            onShowSnackBar = onShowSnackBar,
            onMovieClick = onMovieClick
        )
    }
}