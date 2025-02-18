package com.lwg.favorite

import androidx.lifecycle.ViewModel
import com.lwg.favorite.contract.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(

): ViewModel() {

    private val _favoriteUiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState.Lock)
    val favoriteUiState: StateFlow<FavoriteUiState> get() =  _favoriteUiState
}