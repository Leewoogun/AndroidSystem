package com.lwg.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lwg.designsystem.component.LwgProgressIndicator
import com.lwg.home.contract.HomeUiEffect
import com.lwg.home.contract.HomeUiState
import com.lwg.model.movie.Movie
import com.lwg.util.Logger

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onShowSnackBar: (String) -> Unit,
    onMovieClick: (Int) -> Unit
) {

    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    Logger.i("isLoading: $isLoading")
    val canScrollForward by remember {
        derivedStateOf { lazyListState.canScrollForward }
    }

    var isFirstCheck by remember { mutableStateOf(true) } // 화면에 처음 도달했는지

    HomeContent(
        homeUiState = homeUiState,
        lazyListState = lazyListState,
        isLoading = isLoading,
        onFavoriteClick = viewModel::updateRemoveFavoriteMovie,
        onMovieClick = onMovieClick
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
        if (!isFirstCheck) {
            if (!canScrollForward) { // 스크롤이 더 나아갈 수 없다면 (최하단에 도착했다면)
                viewModel.nextPage()
            }
        } else {
            isFirstCheck = false
        }
    }
}

@Composable
private fun HomeContent(
    homeUiState: HomeUiState,
    lazyListState: LazyListState,
    isLoading: Boolean,
    onFavoriteClick: (Movie) -> Unit,
    onMovieClick: (Int) -> Unit
) {
    when (homeUiState) {
        HomeUiState.Loading -> { LwgProgressIndicator(modifier = Modifier.fillMaxSize(),) }

        is HomeUiState.HomeData -> {
            HomeScreen(
                movieList = homeUiState.movies,
                lazyListState = lazyListState,
                isLoading = isLoading,
                onFavoriteClick = onFavoriteClick,
                onMovieClick = onMovieClick
            )
        }
    }
}