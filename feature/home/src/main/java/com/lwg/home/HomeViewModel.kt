package com.lwg.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lwg.data_api.MovieRepository
import com.lwg.domain.GetMovieUseCase
import com.lwg.home.contract.HomeUiEffect
import com.lwg.home.contract.HomeUiState
import com.lwg.model.movie.Movie
import com.lwg.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {
    private val moviePage = MutableStateFlow(1)

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState : StateFlow<HomeUiState> = _homeUiState.onStart {
        getMovieList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState.Loading
    )

    private val _homeUiEffect: MutableSharedFlow<HomeUiEffect> = MutableSharedFlow()
    val homeUiEffect: SharedFlow<HomeUiEffect> get() = _homeUiEffect

    private fun getMovieList() {
        viewModelScope.launch {
            moviePage.flatMapLatest { page ->
                getMovieUseCase(
                    page = page,
                    onError = ::showSnackBar
                )
            }.collectLatest { movies ->
                _homeUiState.update {
                    when (it) {
                        HomeUiState.Loading -> {
                            HomeUiState.HomeData(movies)
                        }

                        is HomeUiState.HomeData -> {
                            it.copy(it.movies + movies)
                        }
                    }
                }
            }
        }
    }

    fun nextPage() {
        moviePage.update { it + 1 }
    }

    fun updateRemoveFavoriteMovie(movie: Movie) {
        val uiState = _homeUiState.value
        if (uiState !is HomeUiState.HomeData) return

        viewModelScope.launch {
            if (movie.isFavorite) {
                movieRepository.deleteFavoriteMovie(movie.movieId)
                showSnackBar("즐겨찾기에 삭제 되었습니다.")

                _homeUiState.update {
                    uiState.copy(
                        movies = uiState.movies.map {
                            if (it.movieId == movie.movieId) {
                                it.copy(isFavorite = false)
                            } else {
                                it
                            }
                        }
                    )
                }
            } else {
                movieRepository.upsertMovie(movie.copy(isFavorite = true))
                showSnackBar("즐겨찾기에 추가 되었습니다.")

                _homeUiState.update {
                    uiState.copy(
                        movies = uiState.movies.map {
                            if (it.movieId == movie.movieId) {
                                it.copy(isFavorite = true)
                            } else {
                                it
                            }
                        }
                    )
                }
            }
        }
    }

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _homeUiEffect.emit(HomeUiEffect.ShowSnackBar(message))
        }
    }
}