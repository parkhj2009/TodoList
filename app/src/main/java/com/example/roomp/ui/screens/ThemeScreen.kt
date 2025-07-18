package com.example.roomp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomp.ui.components.Card
import com.example.roomp.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeScreen(
    navController: NavController, base: Color, onBaseColorChange: (Color) -> Unit
) {
    Scaffold(
        containerColor = Color.White, topBar = {
            TopAppBar(
                title = { Text("Menu Homepage") }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = base, titleContentColor = Color.White
                )
            )
        }) { innerPadding ->
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
            Card(Color(0xFF4F9F9C)) {
                onBaseColorChange(it)
            }

            Card(Color(0xFF1b1c1f)) {
                onBaseColorChange(it)
            }

            Card(Color(0xFFd85040)) {
                onBaseColorChange(it)
            }

            Card(Color(0xFF3875ea)) {
                onBaseColorChange(it)
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
                    val colorHex = base.value.toUInt().toString(16).padStart(8, '0')
                    Log.d("ThemeScreen", "Received colorHex: $colorHex")
                    navController.navigate(Screen.CreateScreen.withColor(colorHex))
                }) {
                Text("Open Todyapp")
            }
        }
    }
}


