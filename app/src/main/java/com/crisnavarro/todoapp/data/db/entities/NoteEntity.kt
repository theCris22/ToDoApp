package com.crisnavarro.todoapp.data.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crisnavarro.todoapp.core.Constants
import com.crisnavarro.todoapp.data.models.Priority
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.TABLE_NAME)
@Parcelize
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var title: String,
    var priority: Priority,
    var description: String,
) : Parcelable