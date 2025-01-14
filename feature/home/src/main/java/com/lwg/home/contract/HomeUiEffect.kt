package com.lwg.home.contract

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
internal sealed interface HomeUiEffect {

    @Immutable
    data class ShowSnackBar(val message: String) : HomeUiEffect

}