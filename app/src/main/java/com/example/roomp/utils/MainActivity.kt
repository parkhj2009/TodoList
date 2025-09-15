package com.example.roomp.utils

import ThemeViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.roomp.data.AppDatabase
import com.example.roomp.screens.theme.ThemeViewModelFactory
import com.example.roomp.ui.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val appDatabase = AppDatabase.getInstance(this)
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModelFactory(appDatabase)
            )

            // DB에서 가져온 색상을 Compose state로 구독
            val baseColor by themeViewModel.baseColor.collectAsState()

            AppNavGraph(
                navController = navController,
                themeViewModel = themeViewModel,
                onBaseColorChange = { newColor -> themeViewModel.saveTheme(newColor) }
            )
        }
    }
}
