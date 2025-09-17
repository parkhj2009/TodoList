package com.example.roomp.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach

@Composable
fun TaskList(todoViewModel: CreateViewModel) {
    val taskList by todoViewModel.tasks.collectAsState()

    Column {
        Text(text = "할 일 목록", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        if (taskList.isEmpty()) {
            Text("저장된 할 일이 없습니다.", modifier = Modifier.padding(16.dp))
        } else {
            taskList.fastForEach { task ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = task.task, fontWeight = FontWeight.Bold)
                    Text(text = task.description, modifier = Modifier.padding(top = 2.dp, bottom = 2.dp))
                    Text(
                        text = "${task.month}월 ${task.day}일 ${task.hour}시 ${task.min}분",
                        fontSize = 12.sp,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                }
            }
        }
    }
}