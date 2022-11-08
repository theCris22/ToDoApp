package com.crisnavarro.todoapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crisnavarro.todoapp.core.Constants
import com.crisnavarro.todoapp.data.models.Priority
import java.io.Serializable

@Entity(tableName = Constants.TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val priority: Priority,
    val description: String,
):Serializable