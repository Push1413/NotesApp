package com.example.stevdza_san_notes.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(noteTable: NoteTable):Long

    @Update
    suspend fun update(noteTable: NoteTable)

    @Delete
    suspend fun delete(noteTable: NoteTable)

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun getAllNotes():LiveData<List<NoteTable>>

}