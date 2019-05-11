package com.example.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.entities.Note

@Database(entities = arrayOf(Note::class), version = 2)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}