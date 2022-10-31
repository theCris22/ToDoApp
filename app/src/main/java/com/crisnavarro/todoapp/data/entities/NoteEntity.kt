package com.crisnavarro.todoapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crisnavarro.todoapp.core.Constants
import com.crisnavarro.todoapp.data.Priority

@Entity(tableName = Constants.TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val priority: Priority,
    val description: String,
)