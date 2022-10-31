package com.crisnavarro.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crisnavarro.todoapp.data.dao.NoteDao
import com.crisnavarro.todoapp.data.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}