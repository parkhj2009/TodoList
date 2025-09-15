package com.example.roomp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ThemeDao {
    @Query("SELECT * FROM theme WHERE id = 0")
    suspend fun getTheme(): ThemeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTheme(theme: ThemeEntity)
}
