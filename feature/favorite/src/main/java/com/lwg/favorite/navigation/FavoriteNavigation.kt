package com.lwg.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwg.favorite.FavoriteRoute
import com.lwg.navigation.MainBottomBarRoute

fun NavController.navigateToFavorite(
    navOptions: NavOptions? = null
) {
    navigate(MainBottomBarRoute.Favorite)
}

fun NavGraphBuilder.favoriteNavGraph(
    onBackEvent: () -> Unit,
    onShowSnackBar: (String) -> Unit
) {
    composable<MainBottomBarRoute.Favorite> {
        FavoriteRoute()
    }
}