package com.perullheim.homework.presentation.navigation.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.perullheim.homework.presentation.screen.login.loginScreen
import com.perullheim.homework.presentation.screen.login.navigateToLogin
import com.perullheim.homework.presentation.screen.register.navigateToRegister
import com.perullheim.homework.presentation.screen.register.registerScreen
import com.perullheim.homework.presentation.screen.splash.SplashRoute
import com.perullheim.homework.presentation.screen.splash.splashScreen
import com.perullheim.homework.presentation.screen.welcome.navigateToWelcome
import com.perullheim.homework.presentation.screen.welcome.welcomeScreen

@Composable
fun EntryNavigationHost(
    navController: NavHostController,
    mainNavigationHost: @Composable () -> Unit
) {
    NavHost(navController = navController, startDestination = SplashRoute) {

        splashScreen(
            onSessionRestored = navController::navigateToMain,
            onAuthenticationRequired = navController::navigateToWelcome
        )

        welcomeScreen(
            onRegisterClick = navController::navigateToRegister,
            onLoginClick = navController::navigateToLogin
        )

        registerScreen(
            onRegistrationSuccess = navController::navigateToLogin
        )

        loginScreen(
            onLoginSuccess = navController::navigateToMain
        )

        composable<MainRoute> {
            mainNavigationHost()
        }
    }
}