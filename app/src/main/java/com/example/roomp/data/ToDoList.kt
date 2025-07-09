package com.example.roomp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolist")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val task: String,
    val month: Int,
    val day: Int,
    val hour: Int,
    val min: Int
)
