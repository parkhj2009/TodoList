package com.example.roomp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "todolist")
data class ToDoList(
    val task: String,
    val time: Long
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
