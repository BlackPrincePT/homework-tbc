package com.perullheim.homework.presentation.navigation.route

import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavController.navigateToLogin() = navigate(route = LoginRoute)