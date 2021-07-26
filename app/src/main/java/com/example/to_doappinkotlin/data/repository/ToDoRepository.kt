package com.example.to_doappinkotlin.data.repository

import androidx.lifecycle.LiveData
import com.example.to_doappinkotlin.data.ToDoDao
import com.example.to_doappinkotlin.data.models.ToDoData

/* Repository is a class that abstracts the access to mulitple data sources such as DAO and Network
   Repository is not part of Android Architecure component libraries. But it is suggested to be the
   best practice for code separation and architecture for clean API to the rest of the application.
 */

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }
}

