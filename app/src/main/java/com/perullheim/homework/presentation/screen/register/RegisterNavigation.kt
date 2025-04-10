package com.perullheim.homework.presentation.screen.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.perullheim.homework.presentation.screen.login.navigateToLogin
import kotlinx.serialization.Serializable

@Serializable
data object RegisterRoute

fun NavController.navigateToRegister() = navigate(route = RegisterRoute)

fun NavGraphBuilder.registerScreen(
    onRegistrationSuccess: (String, String) -> Unit
) {
    composable<RegisterRoute> {
        RegisterScreen(
            onRegistrationSuccess = onRegistrationSuccess
        )
    }
}