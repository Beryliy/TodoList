package com.example.todolist.model


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.todolist.entities.Note

@Database(entities = arrayOf(Note::class), version = 6)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}