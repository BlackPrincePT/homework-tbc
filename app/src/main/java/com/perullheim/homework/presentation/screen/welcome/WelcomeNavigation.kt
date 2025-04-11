package com.perullheim.homework.presentation.screen.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perullheim.homework.presentation.navigation.util.popNavigate
import kotlinx.serialization.Serializable

@Serializable
object WelcomeRoute

fun NavController.navigateToWelcome() = popNavigate(route = WelcomeRoute)

fun NavGraphBuilder.welcomeScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    composable<WelcomeRoute> {
        WelcomeScreen(
            onRegisterClick = onRegisterClick,
            onLoginClick = onLoginClick
        )
    }
}