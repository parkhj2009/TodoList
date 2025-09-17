package com.example.roomp.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    onDismiss: () -> Unit,
    showDatePicker: () -> Unit,
    onTaskEntered: (task:String, description:String) -> Unit
) {
    var task by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f))
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                    onDismiss()
                })
            }
    ) {
        // Bottom에 Field 고정
        Column (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .imePadding(), // 키보드 높이에 맞춰 위로 올라감
        ) {
            // 할 일
            BasicTextField(
                value = task,
                onValueChange = { input -> task = input.replace("\n", "") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {focusManager.moveFocus(FocusDirection.Down)}
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                    ) {
                        if (task.isEmpty()) {
                            Text("일정을 입력하세요", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(1.dp).background(Color.Black))

            // 설명
            BasicTextField(
                value = description,
                onValueChange = { input -> description = input.replace("\n", "") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (task.isNotBlank() && description.isNotBlank()) {
                            onTaskEntered(task, description)
                            showDatePicker()
                            focusManager.clearFocus()
                        }
                    }
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .background(Color.White, shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (description.isEmpty()) {
                            Text("Description", color = Color.Gray)
                        }
                        innerTextField()
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                            tint = Color.Gray,
                            modifier = Modifier.clickable {
                                if (task.isNotBlank() && description.isNotBlank()) {
                                    onTaskEntered(task, description)
                                    showDatePicker()
                                    focusManager.clearFocus()
                                }
                            }
                        )
                    }
                }
            )
        }
    }
}