package com.perullheim.homework.presentation.screen.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object SplashRoute

fun NavGraphBuilder.splashScreen(
    onSessionRestored: () -> Unit,
    onAuthenticationRequired: () -> Unit
) {
    composable<SplashRoute> {
        SplashScreen(
            onSessionRestored = onSessionRestored,
            onAuthenticationRequired = onAuthenticationRequired
        )
    }
}