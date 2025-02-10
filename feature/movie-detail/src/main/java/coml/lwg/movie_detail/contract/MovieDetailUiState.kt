package coml.lwg.movie_detail.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.lwg.model.movie.MovieDetail

@Stable
internal sealed interface MovieDetailUiState {
    @Immutable
    data object Loading: MovieDetailUiState

    @Immutable
    data class MovieDetailData(
        val movieDetail: MovieDetail,
    ) : MovieDetailUiState
}