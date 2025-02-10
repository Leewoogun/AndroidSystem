package com.lwg.home.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.lwg.model.movie.Movie

@Stable
internal sealed interface HomeUiState {
    @Immutable
    data object Loading: HomeUiState

    @Immutable
    data class HomeData(
        val movies: List<Movie>
    ) : HomeUiState
}