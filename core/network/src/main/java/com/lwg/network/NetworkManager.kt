package com.lwg.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


class NetworkManager @Inject constructor(
    context: Context
) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var isNetworkCallbackRegistered = false

    private val isConnected: Boolean
        @SuppressLint("MissingPermission")
        get() {
            val activeNetwork = connectivityManager.activeNetwork
            return if (activeNetwork == null) {
                false
            } else {
                val netCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
                (netCapabilities != null
                        && netCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        && netCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
            }
        }

    fun connectionStateFlow(externalScope: CoroutineScope): StateFlow<Boolean> {
        return connectionFlow()
            .catch { e -> e.printStackTrace() }
            .stateIn(
                scope = externalScope,
                started = SharingStarted.Lazily,
                initialValue = isConnected
            )
    }

    private fun connectionFlow() = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                trySend(false)
            }

            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    trySend(true)
                }
            }
        }

        subscribe(networkCallback)
        awaitClose { unsubscribe(networkCallback) }
    }

    @SuppressLint("MissingPermission")
    fun subscribe(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        isNetworkCallbackRegistered = true
    }

    private fun unsubscribe(networkCallback: ConnectivityManager.NetworkCallback) {
        if (isNetworkCallbackRegistered) {
            try {
                connectivityManager.unregisterNetworkCallback(networkCallback)
                isNetworkCallbackRegistered = false
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }
}