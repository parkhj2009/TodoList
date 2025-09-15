package com.example.roomp.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomp.data.ToDoDao

class CreateViewModelFactory(private val dao: ToDoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return CreateViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}