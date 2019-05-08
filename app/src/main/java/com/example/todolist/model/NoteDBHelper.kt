package com.example.todolist.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.example.todolist.model.TodoContract.TodoEntry

class NoteDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val SQL_CREATE_ENTRIES = "CREATE TABLE ${TodoEntry.TABLE_NAME}(" +
            "${TodoEntry._ID} INTEGER PRIMARY KEY, " +
            "${TodoEntry.creationTime} INTEGER NOT NULL, " +
            "${TodoEntry.body} TEXT)"
    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TodoEntry.TABLE_NAME}"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "NotesDB"
    }
}