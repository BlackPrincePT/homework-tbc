package com.perullheim.homework.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.perullheim.homework.presentation.navigation.route.HomeRoute
import com.perullheim.homework.presentation.navigation.route.LoginRoute
import com.perullheim.homework.presentation.navigation.route.RegisterRoute
import com.perullheim.homework.presentation.navigation.route.SplashRoute
import com.perullheim.homework.presentation.navigation.route.WelcomeRoute
import com.perullheim.homework.presentation.navigation.route.navigateToHome
import com.perullheim.homework.presentation.navigation.route.navigateToLogin
import com.perullheim.homework.presentation.navigation.route.navigateToRegister
import com.perullheim.homework.presentation.navigation.route.navigateToWelcome
import com.perullheim.homework.presentation.screen.home.HomeScreen
import com.perullheim.homework.presentation.screen.login.LoginScreen
import com.perullheim.homework.presentation.screen.register.RegisterScreen
import com.perullheim.homework.presentation.screen.splash.SplashScreen
import com.perullheim.homework.presentation.screen.welcome.WelcomeScreen

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = SplashRoute) {

        composable<SplashRoute> {
            SplashScreen(
                onSessionRestored = navController::navigateToHome,
                onAuthenticationRequired = navController::navigateToWelcome
            )
        }

        composable<WelcomeRoute> {
            WelcomeScreen(
                onRegisterClick = navController::navigateToRegister,
                onLoginClick = navController::navigateToLogin
            )
        }

        composable<RegisterRoute> {
            RegisterScreen(
                onRegistrationSuccess = navController::navigateToLogin
            )
        }

        composable<LoginRoute> {
            LoginScreen(
                onLoginSuccess = navController::navigateToHome
            )
        }

        composable<HomeRoute> {
            HomeScreen(

            )
        }
    }
}