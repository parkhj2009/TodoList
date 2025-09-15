package com.example.roomp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoEntity::class,ThemeEntity::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tododao(): ToDoDao
    abstract fun themedao(): ThemeDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "App-Database"
                    )
                        .fallbackToDestructiveMigration(true) // DB 스키마 변경 시 기존 DB 삭제 후 생성
                        .build()
                }
            }
            return instance!!
        }
    }
}