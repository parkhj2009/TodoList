package com.example.roomp.ui.components

import ThemeViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.roomp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    themeViewModel: ThemeViewModel,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val base by themeViewModel.baseColor.collectAsState()
    val datePickerState = rememberDatePickerState()

    Column {
        DatePicker(
            state = datePickerState,
            showModeToggle = false, // 텍스트 입력 모드 전환 버튼 숨김
            modifier = Modifier
                .padding(horizontal = 12.dp),
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                selectedDayContentColor = Color.White,
                selectedDayContainerColor = base,
                todayDateBorderColor = base,
                )
        )

        // 버튼 영역
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp)) // 그림자 적용
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .clickable(onClick = {onDismiss()})
            ){
                Text("CANCEL")
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp)) // 그림자 적용
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .clickable(onClick = {
                        onDateSelected(datePickerState.selectedDateMillis)
                    })
            ){
                Text("OK")
            }
        }
    }
}
