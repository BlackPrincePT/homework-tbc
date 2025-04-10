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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.perullheim.homework.R
import com.perullheim.homework.presentation.navigation.components.navBarItems
import com.perullheim.homework.presentation.navigation.core.EntryNavigationHost
import com.perullheim.homework.presentation.navigation.core.MainNavigationHost
import com.perullheim.homework.presentation.screen.welcome.navigateToWelcome
import com.perullheim.homework.presentation.util.ConnectivityManagerNetworkMonitor
import com.perullheim.homework.presentation.util.LaunchLifecycleEffect
import dagger.hilt.android.AndroidEntryPoint
import ge.tkgroup.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: ConnectivityManagerNetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val entryNavController = rememberNavController()
                val mainNavController = rememberNavController()
                val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val snackbarHostState = remember { SnackbarHostState() }
                val isOffline by networkMonitor.isOnline
                    .map(Boolean::not)
                    .collectAsStateWithLifecycle(initialValue = false)

                LaunchLifecycleEffect(isOffline) {
                    if (isOffline)
                        snackbarHostState.showSnackbar(
                            message = getString(R.string.you_aren_t_connected_to_the_internet),
                            duration = SnackbarDuration.Indefinite
                        )
                }

                EntryNavigationHost(navController = entryNavController) {
                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        },
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