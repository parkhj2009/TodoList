package com.example.roomp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomp.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("theme") {
            popUpTo("splash") { inclusive = true }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register/Sign in") },
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 230.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.checklist),
                    contentDescription = "todolist",
                    modifier = Modifier.size(160.dp)
                )
                Text(
                    color = Color.White,
                    text = "Todyapp",
                    fontWeight = FontWeight(900),
                    fontSize = 30.sp
                )
                Text(
                    color = Color.White,
                    text = "The best todo list application for you",
                    fontSize = 14.sp
                )
            }
        }
    }
}
