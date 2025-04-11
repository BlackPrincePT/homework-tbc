package com.perullheim.homework.presentation.screen.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object ProfileRoute

fun NavGraphBuilder.profileScreen(
    onLogoutSuccess: () -> Unit
) {
    composable<ProfileRoute> {
        ProfileScreen(
            onLogOutSuccess = onLogoutSuccess
        )
    }
}