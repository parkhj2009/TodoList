package com.example.roomp.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object ThemeScreen : Screen("theme")
    object CreateScreen : Screen("create/{colorHex}") {
        fun withColor(colorHex:String) = "create/$colorHex"
    }
}