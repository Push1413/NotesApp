package com.example.stevdza_san_notes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.stevdza_san_notes.room.data.Convertor
import com.example.stevdza_san_notes.room.entities.NoteTable

@Database(
    entities = [NoteTable::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertor::class)
abstract class NoteDB : RoomDatabase() {

    abstract fun getNoteDAO(): NoteDAO

}