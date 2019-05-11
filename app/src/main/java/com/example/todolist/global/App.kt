package com.example.todolist.global

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todolist.model.NotesDatabase

class App: Application() {
    private lateinit var dataBase: NotesDatabase
    init{
        instance = this
    }
    override fun onCreate() {
        super.onCreate()
        val context: Context = App.applicationContext()
        dataBase = Room.databaseBuilder(
            this,
            NotesDatabase::class.java, "notes_database"
        ).fallbackToDestructiveMigration().build()
    }

    fun getDatabase() = dataBase

    companion object{
        var instance: App? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}