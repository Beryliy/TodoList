package com.example.todolist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.entities.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    companion object{
        private var notesDatabase: NotesDatabase? = null
        fun getInstance(context: Context): NotesDatabase? {
            if (notesDatabase == null) {
                synchronized(NotesDatabase::class) {
                    notesDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java, "notes_database"
                    ).build()
                }
            }
            return notesDatabase
        }
    }
}