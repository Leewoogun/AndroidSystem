package com.lwg.favorite.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface FavoriteUiState {
    @Immutable
    data object Lock: FavoriteUiState

    @Immutable
    data object Loading: FavoriteUiState
}