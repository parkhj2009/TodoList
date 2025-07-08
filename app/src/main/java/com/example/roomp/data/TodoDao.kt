package com.example.roomp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDao {
    @Insert
    suspend fun insert(todo: ToDoList)

    @Update
    suspend fun update(todo: ToDoList)

    @Delete
    suspend fun delete(todo: ToDoList)

    @Query("SELECT * FROM todolist")
    suspend fun getAll(): List<ToDoList>

    @Query("DELETE FROM todolist")
    suspend fun deleteAll()

}