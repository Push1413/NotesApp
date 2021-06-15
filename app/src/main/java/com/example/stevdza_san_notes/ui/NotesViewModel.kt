package com.example.stevdza_san_notes.ui

import android.app.Application
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.stevdza_san_notes.R
import com.example.stevdza_san_notes.repository.NotesRepository
import com.example.stevdza_san_notes.room.NoteDB
import com.example.stevdza_san_notes.room.data.Priority
import com.example.stevdza_san_notes.room.entities.NoteTable
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    application: Application,
    private val repository: NotesRepository
) : AndroidViewModel(application) {

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green)) }
            }
        }
    }

    private val getAllNotes: LiveData<List<NoteTable>> = repository.getAllNotes()

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

    fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> { Priority.HIGH }
            "Medium Priority" -> { Priority.MEDIUM }
            "Low Priority" -> { Priority.LOW }
            else -> Priority.LOW
        }
    }

}