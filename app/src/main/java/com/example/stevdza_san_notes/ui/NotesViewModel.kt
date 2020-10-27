package com.example.stevdza_san_notes.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.stevdza_san_notes.repository.NotesRepository
import com.example.stevdza_san_notes.room.NoteDB
import com.example.stevdza_san_notes.room.NoteTable
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {

    private val repository:NotesRepository
    private val getAllNotes:LiveData<List<NoteTable>>

    init {
        val notesdao = NoteDB.invoke(application).getNoteDAO()
        repository = NotesRepository(notesdao)
        getAllNotes = repository.getAllNotes()
    }

    fun saveNote(noteTable: NoteTable) = viewModelScope.launch {
        repository.upsert(noteTable)
    }

    fun updateNote(noteTable: NoteTable) = viewModelScope.launch {
        repository.update(noteTable)
    }

    fun deleteNote(noteTable: NoteTable) = viewModelScope.launch {
        repository.delete(noteTable)
    }
    fun getAllSavedNews() = repository.getAllNotes()




}