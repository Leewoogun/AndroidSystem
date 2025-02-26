package com.lwg.favorite.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.lwg.model.movie.Genre
import com.lwg.model.movie.Movie

@Stable
sealed interface FavoriteUiState {
    @Immutable
    data object Lock: FavoriteUiState

    @Immutable
    data object Loading: FavoriteUiState

    @Immutable
    data class Movies(
        val movieList: List<Movie>,
        val genreChipList: List<Genre> = listOf(
            Genre(
                id = 0,
                name = "전체",
                isSelected = true
            ),

            Genre(
                id = 18,
                name = "드라마"
            ),

            Genre(
                id = 80,
                name = "범죄"
            ),

            Genre(
                id = 16,
                name = "애니메이션"
            ),

            Genre(
                id = 35,
                name = "코미디"
            ),

            Genre(
                id = 14,
                name = "판타지"
            ),
        )
    ): FavoriteUiState {


    }

}