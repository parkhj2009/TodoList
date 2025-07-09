package com.example.roomp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert
    suspend fun insert(todo: ToDoEntity)

    @Delete
    suspend fun delete(todo: ToDoEntity)

    @Query("SELECT * FROM todolist")
    fun getAll(): Flow<List<ToDoEntity>>

    @Query("DELETE FROM todolist")
    suspend fun deleteAll()

}