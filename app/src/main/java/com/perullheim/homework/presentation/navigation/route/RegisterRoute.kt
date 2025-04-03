package com.perullheim.homework.presentation.navigation.route

import androidx.navigation.NavController
import com.perullheim.homework.presentation.navigation.util.popNavigate
import kotlinx.serialization.Serializable

@Serializable
data object RegisterRoute

fun NavController.navigateToRegister() = navigate(route = RegisterRoute)