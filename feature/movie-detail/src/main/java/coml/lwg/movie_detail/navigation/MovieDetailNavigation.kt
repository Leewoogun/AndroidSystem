package coml.lwg.movie_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lwg.navigation.Route
import coml.lwg.movie_detail.MovieDetailRoute

fun NavController.navigateToMovieDetail(
    movieId: Int,
    navOptions: NavOptions? = null
) {
    navigate(Route.Detail(movieId), navOptions)
}

fun NavGraphBuilder.movieDetailNavGraph(
    onBackEvent: () -> Unit
) {
    composable<Route.Detail> {
        MovieDetailRoute(
            onBackEvent = onBackEvent
        )
    }
}