package com.lwg.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lwg.data_api.MovieRepository
import com.lwg.favorite.contract.FavoriteUiEffect
import com.lwg.favorite.contract.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        movieRepository.getFavoriteMovie()
    }

    fun showSnackBar(message: String) {
        viewModelScope.launch {
            _favoriteUiEffect.emit(FavoriteUiEffect.ShowSnackBar(message))
        }
    }
}