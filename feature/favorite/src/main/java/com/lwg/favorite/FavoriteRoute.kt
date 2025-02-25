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
import com.lwg.util.BiometricUtil
import com.lwg.util.Logger

@Composable
internal fun FavoriteRoute(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {

    val favoriteUiState by favoriteViewModel.favoriteUiState.collectAsStateWithLifecycle()
    val activity = LocalContext.current as FragmentActivity

    FavoriteContent(
        favoriteUiState = favoriteUiState,
    )

    LaunchedEffect(true) {
        BiometricUtil(activity).checkUseBiometric(
            onSuccess = {
                Logger.i("생체 인식 성공!")
            },
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
            Text("I'm favorite")
        }

        is FavoriteUiState.Movies -> TODO()
    }
}
