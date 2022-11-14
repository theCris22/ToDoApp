package com.crisnavarro.todoapp.ui.notes.viewmodel

import androidx.lifecycle.ViewModel
import com.crisnavarro.todoapp.data.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repository: NotesRepository) : ViewModel() {

    fun getAllNotes() = repository.getAllNotes()
    fun searchNote(query: String) = repository.searchNote(query)
    fun getLowPriorityNotes(priority: String) = repository.getLowPriorityNotes(priority)

}