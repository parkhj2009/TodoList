package com.example.roomp.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roomp.ui.screens.CreateScreen
import com.example.roomp.ui.screens.SplashScreen
import com.example.roomp.ui.screens.ThemeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    baseColor:Color,
    onBaseColorChange: (Color) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(Screen.ThemeScreen.route) {
            ThemeScreen(
                navController = navController,
                base = baseColor,
                onBaseColorChange = onBaseColorChange
            )
        }
        composable(
            Screen.CreateScreen.route,
            arguments = listOf(navArgument("colorHex") { type = NavType.StringType})
            ) { backStackEntry ->
            val colorHex = backStackEntry.arguments?.getString("colorHex") ?: "FF4F9F9C"
            val color = Color(android.graphics.Color.parseColor("$colorHex"))
            CreateScreen(
                navController = navController,
                base = color
            )
        }
    }
}