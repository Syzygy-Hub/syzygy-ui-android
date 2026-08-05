package com.syzygyhub.ui.android.components.feedback

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.syzygyhub.ui.android.theme.LocalSyzygyTheme
import com.syzygyhub.ui.android.theme.SyzygyTheme
import com.syzygyhub.ui.android.tokens.Colors

/** Where a [NetworkStatusBanner] anchors within its container. */
enum class NetworkBannerAlignment { TOP, BOTTOM }

/**
 * Observes device connectivity via a Compose-idiomatic
 * [produceState]-based (DisposableEffect-style `awaitDispose`) wrapper around Android's
 * `ConnectivityManager.NetworkCallback`, registered through
 * `context.getSystemService(ConnectivityManager::class.java)` — first-party
 * Android SDK, no third-party dependency. `true` means online.
 */
@Composable
private fun rememberIsOnline(): Boolean {
    val context = LocalContext.current
    val isOnline by produceState(initialValue = true, context) {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)

        // ACCESS_NETWORK_STATE permission is required by consuming apps — not declared here as this is a library module.
        @SuppressLint("MissingPermission")
        val activeNetwork = connectivityManager?.activeNetwork

        // ACCESS_NETWORK_STATE permission is required by consuming apps — not declared here as this is a library module.
        @SuppressLint("MissingPermission")
        val capabilities = activeNetwork?.let { connectivityManager.getNetworkCapabilities(it) }
        value = capabilities?.hasCapability(android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: true

        val callback =
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    value = true
                }

                override fun onLost(network: Network) {
                    value = false
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: android.net.NetworkCapabilities,
                ) {
                    value = networkCapabilities.hasCapability(android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET)
                }
            }

        // ACCESS_NETWORK_STATE permission is required by consuming apps — not declared here as this is a library module.
        @SuppressLint("MissingPermission")
        val registration = connectivityManager?.registerDefaultNetworkCallback(callback)
        awaitDispose { connectivityManager?.unregisterNetworkCallback(callback) }
    }
    return isOnline
}

/**
 * A top- or bottom-anchored banner reading "No internet connection",
 * appearing automatically when the device goes offline and auto-dismissing
 * once connectivity returns (see [rememberIsOnline]). [manualOverride] can
 * force-show (`true`) or force-hide (`false`) the banner regardless of the
 * actual network state — pass `null` (the default) to defer to real
 * connectivity.
 */
@Composable
fun NetworkStatusBanner(
    modifier: Modifier = Modifier,
    alignment: NetworkBannerAlignment = NetworkBannerAlignment.TOP,
    manualOverride: Boolean? = null,
    message: String = "No internet connection",
    theme: SyzygyTheme? = null,
) {
    val theme = theme ?: LocalSyzygyTheme.current
    val isOnline = rememberIsOnline()
    val isVisible = manualOverride?.let { !it } ?: !isOnline

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { if (alignment == NetworkBannerAlignment.TOP) -it else it },
        exit = slideOutVertically { if (alignment == NetworkBannerAlignment.TOP) -it else it },
        modifier = modifier,
    ) {
        with(Colors) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.destructive)
                        .padding(theme.spacing.sm),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onDestructive,
                )
            }
        }
    }
}
