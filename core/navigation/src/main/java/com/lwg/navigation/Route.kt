package com.lwg.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class Detail(
        val movieId : Int
    ): Route
}

sealed interface MainBottomBarRoute : Route {

    @Serializable
    data object Home: MainBottomBarRoute

    @Serializable
    data object Favorite: MainBottomBarRoute

    @Serializable
    data object Calendar: MainBottomBarRoute
}