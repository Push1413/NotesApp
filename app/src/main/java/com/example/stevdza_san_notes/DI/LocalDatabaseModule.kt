package com.example.stevdza_san_notes.DI

import android.content.Context
import androidx.room.Room
import com.example.stevdza_san_notes.room.NoteDB
import com.example.stevdza_san_notes.utils.Constants.Companion.ROOM_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context)
    = Room.databaseBuilder(
        context,
        NoteDB::class.java,
        ROOM_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: NoteDB) = database.getNoteDAO()

}