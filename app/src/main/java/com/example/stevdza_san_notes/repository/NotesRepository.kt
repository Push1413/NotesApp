package com.example.stevdza_san_notes.repository

import androidx.lifecycle.LiveData
import com.example.stevdza_san_notes.room.NoteDAO
import com.example.stevdza_san_notes.room.NoteTable

class NotesRepository(private val notedao:NoteDAO) {

    suspend fun upsert(noteTable: NoteTable) = notedao.upsert(noteTable)

    suspend fun update(noteTable: NoteTable) = notedao.update(noteTable)

    suspend fun delete(noteTable: NoteTable) = notedao.delete(noteTable)

    fun getAllNotes() = notedao.getAllNotes()


}