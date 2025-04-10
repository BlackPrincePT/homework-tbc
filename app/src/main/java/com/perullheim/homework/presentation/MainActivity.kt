package com.perullheim.homework.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.perullheim.homework.presentation.navigation.components.navBarItems
import com.perullheim.homework.presentation.navigation.core.MainNavigationHost
import com.perullheim.homework.presentation.navigation.core.EntryNavigationHost
import com.perullheim.homework.presentation.screen.welcome.navigateToWelcome
import dagger.hilt.android.AndroidEntryPoint
import ge.tkgroup.myapplication.ui.theme.MyApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val entryNavController = rememberNavController()
                val mainNavController = rememberNavController()
                val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                EntryNavigationHost(navController = entryNavController) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                navBarItems.forEach { item ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                imageVector = item.icon,
                                                contentDescription = null
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any { it.route == item.route::class.qualifiedName } == true,
                                        onClick = {
                                            mainNavController.navigate(item.route) {
                                                popUpTo(mainNavController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        MainNavigationHost(
                            navController = mainNavController,
                            navigateToWelcome = entryNavController::navigateToWelcome,
                            modifier = Modifier
                                .padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}