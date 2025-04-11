package com.perullheim.homework.presentation.navigation.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.perullheim.homework.R
import com.perullheim.homework.presentation.screen.home.HomeRoute
import com.perullheim.homework.presentation.screen.profile.ProfileRoute

data class NavBarItem<T>(
    val route: T,
    val icon: ImageVector,
    @StringRes val nameRes: Int
)

val navBarItems = listOf(
    NavBarItem(
        route = HomeRoute,
        icon = Icons.Default.Home,
        nameRes = R.string.home
    ),
    NavBarItem(
        route = ProfileRoute,
        icon = Icons.Default.Person,
        nameRes = R.string.profile
    )
)