package com.example.roomp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoList::class], version = 1)
abstract class Appdatabase: RoomDatabase() {
    abstract fun tododao(): ToDoDao

    companion object {
        private var instance: Appdatabase? = null

        @Synchronized
        fun getInstance(context: Context): Appdatabase? {
            if (instance == null) {
                synchronized(Appdatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Appdatabase::class.java,
                        "App-database"
                    ).build()
                }
            }
            return instance
        }
    }
}