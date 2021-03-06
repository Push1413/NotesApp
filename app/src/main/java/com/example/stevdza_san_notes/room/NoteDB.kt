package com.example.stevdza_san_notes.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NoteTable::class],version = 1)
abstract class NoteDB:RoomDatabase() {

    abstract fun getNoteDAO(): NoteDAO
    companion object{
        @Volatile
        private var instance: NoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java,
                "Notes_db.db"
            ).build()
    }
}