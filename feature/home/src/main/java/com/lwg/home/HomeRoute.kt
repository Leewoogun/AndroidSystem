package com.lwg.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lwg.home.contract.HomeUiEffect
import com.lwg.home.contract.HomeUiState
import com.lwg.util.Logger

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onShowSnackBar: (String) -> Unit
) {

    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    val canScrollForward by remember {
        derivedStateOf { lazyListState.canScrollForward }
    }

    HomeContent(
        homeUiState = homeUiState,
        lazyListState = lazyListState
    )

    LaunchedEffect(Unit) {
        viewModel.homeUiEffect.collect { effect ->
            when (effect) {
                is HomeUiEffect.ShowSnackBar -> onShowSnackBar(effect.message)
            }
        }
    }

    LaunchedEffect(
        key1 = canScrollForward
    ) {
        if (!canScrollForward) { // 스크롤이 더 나아갈 수 없다면 (최하단에 도착했다면)
            viewModel.nextPage()
        }
    }
}

@Composable
private fun HomeContent(
    homeUiState: HomeUiState,
    lazyListState: LazyListState
) {
    when (homeUiState) {
        HomeUiState.Loading -> {}

        is HomeUiState.HomeData -> {
            HomeScreen(
                movieList = homeUiState.movies,
                lazyListState = lazyListState
            )
        }
    }
}