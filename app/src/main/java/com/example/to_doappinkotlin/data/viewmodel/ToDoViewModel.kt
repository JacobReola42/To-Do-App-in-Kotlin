package com.example.to_doappinkotlin.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_doappinkotlin.data.ToDoDatabase
import com.example.to_doappinkotlin.data.models.ToDoData
import com.example.to_doappinkotlin.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
ViewModel role is to provide data to the UI and surivive configuration changes. A viewModel acts
as a communication center between the Repository and the UI.
 */

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository: ToDoRepository = ToDoRepository(toDoDao)
    val getAllData: LiveData<List<ToDoData>> = repository.getAllData

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData) //background thread for coroutine for sql queries
        }
    }
}