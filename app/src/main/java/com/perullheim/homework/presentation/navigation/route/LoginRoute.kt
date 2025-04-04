package com.perullheim.homework.presentation.navigation.route

import androidx.navigation.NavController
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