package com.example.roomp.screens.create

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomp.data.ToDoDao
import com.example.roomp.data.ToDoEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CreateViewModel(private var dao: ToDoDao) : ViewModel() {

    private var _tasks = MutableStateFlow<List<ToDoEntity>>(emptyList())
    val tasks: StateFlow<List<ToDoEntity>> = _tasks


    init {
        viewModelScope.launch {
            dao.getAll().collectLatest {
                _tasks.value = it
            }
        }
    }

    fun insertTodo(task: String, des:String ,month: Int, day: Int, hour: Int, min: Int) {
        viewModelScope.launch {
            Log.d("TodoViewModel", "Inserting task: $task at $month/$day $hour:$min")
            dao.insert(ToDoEntity(task = task, description = des, month = month, day = day, hour = hour, min = min))
        }
    }

    fun delAll() {
        viewModelScope.launch {
            dao.deleteAll()
        }
    }
}