package com.crisnavarro.todoapp.data

import com.crisnavarro.todoapp.data.db.dao.NoteDao
import com.crisnavarro.todoapp.data.db.entities.NoteEntity
import javax.inject.Inject


class NotesRepository @Inject constructor(private val notesDao: NoteDao) {

    fun getAllNotes() = notesDao.getAllNotes()
    suspend fun insertNote(note: NoteEntity) = notesDao.insertNote(note)
    suspend fun deleteNote(note: NoteEntity) = notesDao.deleteNote(note)

}