package com.perullheim.homework.presentation.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.perullheim.homework.presentation.navigation.util.popNavigate
import com.perullheim.homework.presentation.screen.home.HomeRoute
import com.perullheim.homework.presentation.screen.home.homeScreen
import com.perullheim.homework.presentation.screen.profile.profileScreen
import com.perullheim.homework.presentation.screen.welcome.navigateToWelcome
import kotlinx.serialization.Serializable

@Serializable
data object MainRoute

fun NavController.navigateToMain() = popNavigate(MainRoute)

@Composable
fun MainNavigationHost(
    navController: NavHostController,
    navigateToWelcome: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
        homeScreen()

        profileScreen(onLogoutSuccess = navigateToWelcome)
    }
}