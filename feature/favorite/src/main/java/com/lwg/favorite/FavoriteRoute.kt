package com.lwg.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lwg.favorite.contract.FavoriteUiState
import com.lwg.ui.LockScreen
import com.lwg.util.BiometricUtil
import com.lwg.util.Logger

@Composable
internal fun FavoriteRoute(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteUiState by viewModel.favoriteUiState.collectAsStateWithLifecycle()
    val activity = LocalContext.current as FragmentActivity

    FavoriteContent(
        favoriteUiState = favoriteUiState,
    )

    LaunchedEffect(true) {
        BiometricUtil(activity).checkUseBiometric(
            onSuccess = viewModel::getFavoriteMovies,
            onFailure = {
                Logger.i("생체 인식 실패!!")
            }
        )
    }
}

@Composable
private fun FavoriteContent(
    favoriteUiState: FavoriteUiState,
) {
    when(favoriteUiState) {
        FavoriteUiState.Loading -> {
            Text("I'm favorite")
        }
        FavoriteUiState.Lock -> {
            LockScreen()
        }

        is FavoriteUiState.Movies -> {
            FavoriteScreen(
                uiState = favoriteUiState
            )
        }
    }
}
