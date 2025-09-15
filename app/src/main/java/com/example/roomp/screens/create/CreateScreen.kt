package com.example.roomp.screens.create

import ThemeViewModel
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roomp.ui.components.CustomTextField
import com.example.roomp.ui.components.DatePickerModal
import com.example.roomp.ui.components.FullScreenTimePicker
import com.example.roomp.ui.components.TaskListScreen
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel,
    todoViewModel: CreateViewModel = viewModel()
) {
    val base by themeViewModel.baseColor.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var showInput by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedTimeMills by remember { mutableStateOf<TimePickerState?>(null) }
    var inputTask by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = Color.White, bottomBar = {
                BottomAppBar {
                    BottomNavigationBar(navController = navController)
                }
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(),
                        start = 24.dp,
                        end = 24.dp
                    ), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top
            ) {
                val today = Date()
                val formatter = SimpleDateFormat("EEE d MMMM yyyy", Locale.ENGLISH)
                val formattedDate = formatter.format(today)

                Text(
                    modifier = Modifier.padding(10.dp), text = "Today", fontSize = 30.sp
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Best platform for creating to-do list",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))

                Box {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth()
                            .height(148.dp)
                            .shadow(
                                elevation = 8.dp, shape = RoundedCornerShape(16.dp), clip = true
                            )
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .clickable { showInput = true }
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth()
                            .height(36.dp)
                            .background(
                                base, shape = RoundedCornerShape(
                                    topStart = 16.dp,
                                    topEnd = 16.dp
                                )
                            )

                    )

                    Column {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(top = 60.dp)
                                .fillMaxWidth(),
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(base, RoundedCornerShape(6.dp)),
                                imageVector = Icons.Filled.Add,
                                contentDescription = "plus",
                                tint = Color.White,
                            )

                            Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                            Text(
                                text = "Tap plus to creat a new task",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .padding(top = 20.dp) // Row 아래에서 약간 띄움
                                .height(1.dp)
                                .background(Color.Gray)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),  // 위아래 여백 조정
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Add your task",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.width(30.dp)) // 두 텍스트 사이 간격 조정

                            Text(
                                text = formattedDate,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                color = Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(top = 30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    TaskListScreen(todoViewModel = todoViewModel)
                }
            }
        }
    }

    if (showInput) {
        CustomTextField(
            onDismiss = { showInput = false },
            showDatePicker = { showDatePicker = true },
            onTaskEntered = { task ->
                inputTask = task
                Log.d("entered", "received: $task")
            })
    }

    if (showDatePicker) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = {
                showInput = false
                showDatePicker = false
            },
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                DatePickerModal(
                    onDateSelected = {
                        selectedDateMillis = it
                        showTimePicker = true
                        showDatePicker = false
                    },
                    onDismiss = {
                        showDatePicker = false
                    }
                )
            }
        }
    }

    if (showTimePicker) {
        FullScreenTimePicker(onConfirm = {
            selectedTimeMills = it
            showTimePicker = false
            showInput = false
            selectedDateMillis?.let { dateMills ->
                val date = Date(dateMills)
                val calendar = Calendar.getInstance().apply { time = date }

                val month = calendar.get(Calendar.MONTH) + 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val hour = it.hour
                val min = it.minute

                if (inputTask.isNotBlank()) {
                    todoViewModel.insertTodo(inputTask, description, month, day, hour, min)
                }
            }
        }, onDismiss = {
            showTimePicker = false
            showDatePicker = true
            showInput = false
        })
    }
}
