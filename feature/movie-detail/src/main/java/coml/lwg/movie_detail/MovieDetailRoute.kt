package coml.lwg.movie_detail

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lwg.designsystem.component.LwgProgressIndicator
import com.lwg.designsystem.theme.IsChangeStatusBarWhite
import coml.lwg.movie_detail.contract.MovieDetailUiState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val movieDetailUiState by viewModel.movieDetailUiState.collectAsStateWithLifecycle()

    MovieDetailContent(
        movieDetailUiState = movieDetailUiState
    )

    IsChangeStatusBarWhite(
        isWhite = true
    )
}

@Composable
private fun MovieDetailContent(
    movieDetailUiState: MovieDetailUiState
) {
    when (movieDetailUiState) {
        MovieDetailUiState.Loading -> {  }
        is MovieDetailUiState.MovieDetailData -> {
            MovieDetailScreen(
                movieDetailUiState = movieDetailUiState
            )
        }
    }
}

