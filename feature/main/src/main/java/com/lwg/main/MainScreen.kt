package com.lwg.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lwg.main.component.MainBottomBar
import com.lwg.main.component.NetworkConnectionBox

@Composable
internal fun MainScreen(
    navigator: MainNavigator,
    snackBarHostState: SnackbarHostState,
    networkState: Boolean,
    onShowSnackBar: (String) -> Unit
) {
    MainScreenContent(
        navigator = navigator,
        snackBarHostState = snackBarHostState,
        networkState = networkState,
        onShowSnackBar = onShowSnackBar
    )
}

@Composable
private fun MainScreenContent(
    navigator: MainNavigator,
    snackBarHostState: SnackbarHostState,
    networkState: Boolean,
    onShowSnackBar: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                MainNavHost(
                    navigator = navigator,
                    padding = paddingValues,
                    onShowSnackBar = onShowSnackBar
                )

                NetworkConnectionBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    networkState = networkState
                )
            }
        },
        bottomBar = {
            MainBottomBar(navigator)
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .imePadding()
            )
        }
    )
}