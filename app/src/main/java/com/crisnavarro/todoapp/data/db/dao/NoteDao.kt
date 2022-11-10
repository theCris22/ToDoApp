package com.crisnavarro.todoapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crisnavarro.todoapp.data.db.entities.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

}