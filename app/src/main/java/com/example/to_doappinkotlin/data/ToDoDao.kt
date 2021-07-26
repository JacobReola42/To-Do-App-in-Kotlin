package com.example.to_doappinkotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*

// for ROOM to identify DAO
@Dao
interface ToDoDao {

    // SQLite Syntax
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    // Duplicate Item ROOM will do something
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    // co-routine run on background thread
    @Update
    suspend fun updateData(toDoData: ToDoData)

}