package com.perullheim.homework.presentation.navigation.route

import androidx.navigation.NavController
import com.perullheim.homework.presentation.navigation.util.popNavigate
import kotlinx.serialization.Serializable

@Serializable
object WelcomeRoute

fun NavController.navigateToWelcome() = popNavigate(route = WelcomeRoute)