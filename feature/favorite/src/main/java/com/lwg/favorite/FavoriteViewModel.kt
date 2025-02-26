package com.lwg.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lwg.data_api.MovieRepository
import com.lwg.favorite.contract.FavoriteUiEffect
import com.lwg.favorite.contract.FavoriteUiState
import com.lwg.model.movie.Genre
import com.lwg.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _favoriteUiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState.Lock)
    val favoriteUiState: StateFlow<FavoriteUiState> get() = _favoriteUiState

    private val _favoriteUiEffect = MutableSharedFlow<FavoriteUiEffect>()
    val favoriteUiEffect: MutableSharedFlow<FavoriteUiEffect> get() = _favoriteUiEffect

    fun getFavoriteMovies() {
        viewModelScope.launch {
            movieRepository.getFavoriteMovie().collect { movies ->
                _favoriteUiState.update {
                    FavoriteUiState.Movies(
                        movieList = movies
                    )
                }
            }
        }
    }

    fun deleteFavoriteMovie(movieId: Int) {
        viewModelScope.launch {
            movieRepository.deleteFavoriteMovie(movieId)
        }
    }

    fun genreClick(genreId: Int) {
        val currentState = _favoriteUiState.value
        if (currentState !is FavoriteUiState.Movies) return

        changeGenreSelected(currentState, genreId)
        checkGenreSelected()
    }

    private fun changeGenreSelected(state: FavoriteUiState.Movies, genreId: Int) {
        if (genreId == 0) {
            _favoriteUiState.update {
                state.copy(
                    genreChipList = defaultGenreList
                )
            }
        } else {
            _favoriteUiState.update {
                state.copy(
                    genreChipList = state.genreChipList.map { genre ->
                        genre.copy(
                            isSelected = when (genre.id) {
                                0 -> false
                                genreId -> !genre.isSelected
                                else -> genre.isSelected
                            }
                        )
                    }
                )
            }

        }
    }

    private fun checkGenreSelected() {
        val currentState = _favoriteUiState.value
        if (currentState !is FavoriteUiState.Movies) return

        if (currentState.genreChipList.none { it.isSelected }) {
            _favoriteUiState.update {
                currentState.copy(
                    genreChipList = defaultGenreList
                )
            }
        }
    }

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _favoriteUiEffect.emit(FavoriteUiEffect.ShowSnackBar(message))
        }
    }

    companion object {
        val defaultGenreList: List<Genre> = listOf(
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
    }
}