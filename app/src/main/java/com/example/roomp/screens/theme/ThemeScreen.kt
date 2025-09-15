package com.example.roomp.screens.theme

import ThemeViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomp.ui.components.Card
import com.example.roomp.ui.navigation.Screen

@Composable
fun ThemeScreen(
    navController: NavController, themeViewModel: ThemeViewModel,onBaseColorChange: (Color) -> Unit
) {
    val base by themeViewModel.baseColor.collectAsState()
    Scaffold(contentColor = Color.White) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(
                    top = innerPadding.calculateTopPadding()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Creat to do list",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                textAlign = TextAlign.Center,

                fontSize = 40.sp
            )
            Text(
                text = "Choose your to do list color theme:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,

                fontSize = 15.sp
            )

            //테마 선택
            listOf(
                Color(0xFF4F9F9C),
                Color(0xFF1b1c1f),
                Color(0xFFd85040),
                Color(0xFF3875ea)
            ).forEach { color ->
                Card(color) { themeViewModel.saveTheme(color)}
            }

            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .padding(30.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = base, // 버튼 배경색
                    contentColor = Color.White          // 텍스트 색상
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate(Screen.CreateScreen.route)
                }) {
                Text("Open Todyapp")
            }
        }
    }
}


