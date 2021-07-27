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
    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData){
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return toDoDao.searchDatabase(searchQuery)
    }

}

