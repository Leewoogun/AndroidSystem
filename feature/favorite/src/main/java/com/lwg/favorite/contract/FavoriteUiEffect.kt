package com.lwg.favorite.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface FavoriteUiEffect {

    @Immutable
    data class ShowSnackBar(val message: String) : FavoriteUiEffect
}