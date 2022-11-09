package com.crisnavarro.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.crisnavarro.todoapp.data.db.converters.Converter
import com.crisnavarro.todoapp.data.db.dao.NoteDao
import com.crisnavarro.todoapp.data.db.entities.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class NotesDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}