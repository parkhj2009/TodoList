package com.example.roomp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector?) {
    object SplashScreen : Screen("splash","splash", null)
    object ThemeScreen : Screen("theme","theme",Icons.Filled.Home)
    object CreateScreen : Screen("create", "create", Icons.Rounded.DateRange)
}