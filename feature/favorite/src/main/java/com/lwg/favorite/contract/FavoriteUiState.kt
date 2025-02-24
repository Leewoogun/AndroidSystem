package com.lwg.favorite.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.lwg.model.movie.Movie

@Stable
sealed interface FavoriteUiState {
    @Immutable
    data object Lock: FavoriteUiState

    @Immutable
    data object Loading: FavoriteUiState

    @Immutable
    data class Movies(
        val movieList: List<Movie>
    ): FavoriteUiState
}