package com.crisnavarro.todoapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crisnavarro.todoapp.data.db.entities.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: NoteEntity): Long

    @Delete
    suspend fun deleteNote(note: NoteEntity)

}