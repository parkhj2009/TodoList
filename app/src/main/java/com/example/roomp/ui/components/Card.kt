package com.example.roomp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Card(base: Color, onClick: (Color) -> Unit) {
    Box(
        modifier = Modifier
            .padding(
                top = 20.dp
            )
            .width(360.dp)
            .height(100.dp)
            .clickable { onClick(base) }) {
        // 흰 부분
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(360.dp)
                .height(100.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = true // 둥근 그림자와 함께 모양을 잘라줌
                )
                .background(Color.White, shape = RoundedCornerShape(16.dp))
        )

        //색 부분
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(360.dp)
                .height(30.dp)
                .background(
                    base, shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )

        )

    }
}