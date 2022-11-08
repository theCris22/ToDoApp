package com.crisnavarro.todoapp.ui.addnotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crisnavarro.todoapp.data.NotesRepository
import com.crisnavarro.todoapp.data.db.entities.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    fun insert(note: NoteEntity) = viewModelScope.launch {
        repository.insertNote(note)
    }

}