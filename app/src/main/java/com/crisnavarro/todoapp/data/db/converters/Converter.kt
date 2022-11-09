package com.crisnavarro.todoapp.data.db.converters

import androidx.room.TypeConverter
import com.crisnavarro.todoapp.data.models.Priority

object Converter {

    @TypeConverter
    fun fromPriority(priority: Priority) = priority.name

    @TypeConverter
    fun toPriority(priority: String) = Priority.valueOf(priority)

}