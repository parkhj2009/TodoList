package com.example.roomp.ui.components

import ThemeViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    themeViewModel: ThemeViewModel,
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit
) {
    val base by themeViewModel.baseColor.collectAsState()
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(8.dp, RoundedCornerShape(24.dp))
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .padding(20.dp)
                .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }){ }
        ) {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    containerColor = Color.White,
                    selectorColor = base,
                    clockDialColor = Color(0xFFF3F5F9),
                    periodSelectorSelectedContentColor = Color.White,
                    periodSelectorUnselectedContentColor = Color.White.copy(alpha = 0.3f),
                    periodSelectorBorderColor = Color.White.copy(alpha = 0f),
                    periodSelectorUnselectedContainerColor = base,
                    periodSelectorSelectedContainerColor = base,
                    clockDialSelectedContentColor = Color.White,
                    timeSelectorSelectedContainerColor = base,
                    timeSelectorUnselectedContainerColor = base,
                    timeSelectorUnselectedContentColor = Color.White,
                    timeSelectorSelectedContentColor = Color.White,
                )

            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(80.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .clickable(onClick = { onDismiss() }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CANCEL",
                        fontSize = 16.sp,
                        color = base
                    )
                }

                Spacer(modifier = Modifier.padding(horizontal = 20.dp))

                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(80.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .clickable(onClick = { onConfirm(timePickerState) }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "OK",
                        fontSize = 16.sp,
                        color = base
                    )
                }
            }
        }
    }
}