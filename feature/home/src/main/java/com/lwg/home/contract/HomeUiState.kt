package com.lwg.home.contract

import androidx.compose.runtime.Stable
import com.lwg.model.movie.Movie

@Stable
internal sealed interface HomeUiState {
    @Stable
    data object Loading: HomeUiState

    @Stable
    data class HomeData(
        val movies: List<Movie>
    ) : HomeUiState
}