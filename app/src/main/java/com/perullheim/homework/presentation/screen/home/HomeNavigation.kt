package com.perullheim.homework.presentation.screen.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.homeScreen(

) {
    composable<HomeRoute> {
        HomeScreen()
    }
}