package com.example.roomp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme")
data class ThemeEntity(
    @PrimaryKey val id: Int = 0,
    val baseColor: Int
)