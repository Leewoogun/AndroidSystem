package coml.lwg.movie_detail

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lwg.designsystem.theme.IsChangeStatusBarWhite
import com.lwg.model.movie.Review
import com.lwg.util.Logger
import coml.lwg.movie_detail.contract.MovieDetailUiEffect
import coml.lwg.movie_detail.contract.MovieDetailUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun MovieDetailRoute(
    onBackEvent: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val movieDetailUiState by viewModel.movieDetailUiState.collectAsStateWithLifecycle()
    val reviewData by viewModel.reviewData.collectAsStateWithLifecycle()

    var isShowPosterImage by remember { mutableStateOf(true) }
    val view = LocalView.current

    MovieDetailContent(
        movieDetailUiState = movieDetailUiState,
        reviewData = reviewData,
        isShowPosterImage = isShowPosterImage,
        onCollapsed = { isShowPosterImage = false },
        onExpanded = { isShowPosterImage = true },
        onBackEvent = onBackEvent
    )

    LaunchedEffect(Unit) {
        viewModel.movieDetailUiEffect.collectLatest {
            when (it) {
                is MovieDetailUiEffect.ShowSnackBar -> { Logger.i("설마 여기로? ${it.message}") }
            }
        }
    }

    LaunchedEffect(isShowPosterImage) {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isShowPosterImage
    }
}

@Composable
private fun MovieDetailContent(
    movieDetailUiState: MovieDetailUiState,
    reviewData: Review,
    isShowPosterImage: Boolean,
    onCollapsed: () -> Unit,
    onExpanded: () -> Unit,
    onBackEvent: () -> Unit
) {
    when (movieDetailUiState) {
        MovieDetailUiState.Loading -> {  }
        is MovieDetailUiState.MovieDetailData -> {
            MovieDetailScreen(
                movieDetailUiState = movieDetailUiState,
                reviewData = reviewData,
                isShowPosterImage = isShowPosterImage,
                onCollapsed = onCollapsed,
                onExpanded = onExpanded,
                onBackEvent = onBackEvent
            )
        }
    }
}

