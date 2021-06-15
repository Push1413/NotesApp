package com.example.stevdza_san_notes.room.data

import androidx.room.TypeConverter

class Convertor {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}