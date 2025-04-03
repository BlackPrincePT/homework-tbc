package com.perullheim.homework.presentation.navigation.route

import androidx.navigation.NavController
import com.perullheim.homework.presentation.navigation.util.popNavigate
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavController.navigateToHome() = popNavigate(route = HomeRoute)