package com.example.to_doappinkotlin.data

import androidx.room.TypeConverter
import com.example.to_doappinkotlin.data.models.Priority

// For ToDoData.kt priority and description
class Converter {

    // high, medium, low
    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    // Convert priority string with specified name
    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }


}