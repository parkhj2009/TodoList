package com.example.roomp.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.roomp.data.AppDatabase
import com.example.roomp.ui.screens.CreateScreen
import com.example.roomp.ui.screens.SplashScreen
import com.example.roomp.ui.screens.ThemeScreen
import com.example.roomp.ui.screens.TodoViewModel
import com.example.roomp.ui.screens.TodoViewModelFactory

@Composable
fun AppNavGraph(
    navController: NavHostController, baseColor: Color, onBaseColorChange: (Color) -> Unit
) {
    NavHost(
        navController = navController, startDestination = Screen.SplashScreen.route
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
        composable(Screen.CreateScreen.route) {

            val dao = AppDatabase.getInstance(context = LocalContext.current).tododao()

            val todoViewModel: TodoViewModel = viewModel(
                factory = TodoViewModelFactory(dao)
            )

            CreateScreen(
                navController = navController, base = baseColor, todoViewModel = todoViewModel
            )
        }
    }
}