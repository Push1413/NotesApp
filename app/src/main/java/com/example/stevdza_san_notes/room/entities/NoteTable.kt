package com.example.stevdza_san_notes.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stevdza_san_notes.room.data.Priority
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Notes")
@Parcelize
data class NoteTable (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    val Title : String,
    val Body:String,
    val priority: Priority
):Parcelable