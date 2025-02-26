package com.lwg.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.lwg.designsystem.theme.LwgTheme
import com.lwg.main.component.rememberMainNavigator
import com.lwg.network.NetworkManager
import com.lwg.util.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: FragmentActivity() {

    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val networkState by networkManager.connectionStateFlow(lifecycleScope).collectAsStateWithLifecycle()
            val navigator = rememberMainNavigator()
            val snackBarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            val onShowSnackBar: (String) -> Unit = { message ->
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(
                        message = message,
                        withDismissAction = true
                    )
                }
            }
            Logger.i("isShow: ${navigator.shouldShowBottomBar()}")
            LwgTheme {
                MainScreen(
                    navigator = navigator,
                    snackBarHostState = snackBarHostState,
                    networkState = networkState,
                    onShowSnackBar = onShowSnackBar
                )
            }
        }
    }
}