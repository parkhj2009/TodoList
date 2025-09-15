package com.example.roomp.ui.navigation


import ThemeViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.roomp.data.AppDatabase
import com.example.roomp.screens.create.CreateScreen
import com.example.roomp.screens.create.CreateViewModel
import com.example.roomp.screens.create.CreateViewModelFactory
import com.example.roomp.screens.splash.SplashScreen
import com.example.roomp.screens.theme.ThemeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    themeViewModel: ThemeViewModel,
    onBaseColorChange: (Color) -> Unit
) {
    val context = LocalContext.current
    val appDatabase = AppDatabase.getInstance(context)
    NavHost(
        navController = navController, startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController,
                appDatabase = appDatabase,
                themeViewModel = themeViewModel
            )
        }
        composable(Screen.ThemeScreen.route) {
            ThemeScreen(
                navController = navController,
                themeViewModel = themeViewModel,
                onBaseColorChange = onBaseColorChange
            )

        }
        composable(Screen.CreateScreen.route) {

            val dao = AppDatabase.getInstance(context = LocalContext.current).tododao()

            val todoViewModel: CreateViewModel = viewModel(
                factory = CreateViewModelFactory(dao)
            )

            CreateScreen(
                navController = navController,
                themeViewModel = themeViewModel,
                todoViewModel = todoViewModel
            )
        }
    }
}