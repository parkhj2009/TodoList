package com.example.roomp.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.roomp.ui.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var baseColor by remember { mutableStateOf(Color(0xFF4F9F9C)) }
            val navController = rememberNavController()

            AppNavGraph(
                navController = navController,
                baseColor = baseColor,
                onBaseColorChange = { newColor -> baseColor = newColor })
        }
    }
}