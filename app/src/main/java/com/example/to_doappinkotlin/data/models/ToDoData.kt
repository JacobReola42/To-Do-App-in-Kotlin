// Dao Database

package com.example.to_doappinkotlin.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.to_doappinkotlin.data.models.Priority

@Entity(tableName = "todo_table")
data class ToDoData (
    // Generates id column by ROOM library
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    // linked with Converter.kt
    var priority: Priority,
    var description: String
)