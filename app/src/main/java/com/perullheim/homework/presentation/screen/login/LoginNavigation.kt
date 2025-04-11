package com.perullheim.homework.presentation.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class LoginRoute(
    val email: String? = null,
    val password: String? = null
)

fun NavController.navigateToLogin(
    email: String? = null,
    password: String? = null
) {
    navigate(route = LoginRoute(email, password))
}

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit
) {
    composable<LoginRoute> {
        LoginScreen(
            onLoginSuccess = onLoginSuccess
        )
    }
}