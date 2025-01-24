package com.lwg.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.lwg.calendar.navigation.calendarNavGraph
import com.lwg.favorite.navigation.favoriteNavGraph
import com.lwg.home.navigation.homeNavGraph
import coml.lwg.movie_detail.navigation.movieDetailNavGraph


@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
    onShowSnackBar: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            homeNavGraph(
                onBackEvent = {},
                onShowSnackBar = onShowSnackBar,
                onMovieClick = navigator::navigateToMovieDetail
            )

            favoriteNavGraph(
                onBackEvent = {},
                onShowSnackBar = onShowSnackBar
            )

            calendarNavGraph(
                onBackEvent = {},
                onShowSnackBar = onShowSnackBar
            )

            movieDetailNavGraph(

            )
        }
    }
}