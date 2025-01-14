package com.lwg.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lwg.data_api.MovieRepository
import com.lwg.home.contract.HomeUiEffect
import com.lwg.home.contract.HomeUiState
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
    private val movieRepository: MovieRepository
) : ViewModel() {
    val moviePage = MutableStateFlow(1)

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
                movieRepository.getTopRatedMovies(
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

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _homeUiEffect.emit(HomeUiEffect.ShowSnackBar(message))
        }
    }
}