package com.lwg.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lwg.home.contract.HomeUiEffect
import com.lwg.home.contract.HomeUiState

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onShowSnackBar: (String) -> Unit
) {

    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    HomeContent(
        homeUiState = homeUiState
    )

    LaunchedEffect(Unit) {
        viewModel.homeUiEffect.collect { effect ->
            when (effect) {
                is HomeUiEffect.ShowSnackBar -> onShowSnackBar(effect.message)
            }
        }
    }
}

@Composable
private fun HomeContent(
    homeUiState: HomeUiState
) {
    when (homeUiState) {
        HomeUiState.Loading -> {}

        is HomeUiState.HomeData -> {
            HomeScreen(
                movieList = homeUiState.movies
            )
        }
    }
}