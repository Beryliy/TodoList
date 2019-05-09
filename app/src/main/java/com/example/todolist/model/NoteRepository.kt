package com.example.todolist.model

import android.app.Application
import android.os.AsyncTask
import com.example.todolist.entities.Note

class NoteRepository(application: Application) {
    private val dao: NoteDao

    init{
        val database = NotesDatabase.getInstance(application.applicationContext)
        dao = database!!.noteDao()
    }

    fun insertNotes(note: Note){
        InsertAsyncTask(dao).execute(note)
    }

    fun updateNote(note: Note){
        UpdateAsyncTask(dao).execute(note)
    }

    fun deleteNote(note: Note){
        DeleteAsyncTask(dao).execute(note)
    }

    fun getAllNodes(){
        //Implement load data using ViewModel
    }

    fun getNodesById(id: Int) = GetByIdAsyncTask(dao).execute(id)

    private class InsertAsyncTask(val dao: NoteDao): AsyncTask<Note, Unit, Unit>(){
        override fun doInBackground(vararg params: Note) {
            dao.insertAll(params[0]!!)
        }
    }

    private class UpdateAsyncTask(val dao: NoteDao): AsyncTask<Note, Unit, Unit>(){
        override fun doInBackground(vararg params: Note?) {
            dao.update(params[0]!!)
        }
    }

    private class DeleteAsyncTask(val dao: NoteDao): AsyncTask<Note, Unit, Unit>(){
        override fun doInBackground(vararg params: Note?) {
            dao.delete(params[0]!!)
        }
    }

    private class GetByIdAsyncTask(val dao: NoteDao): AsyncTask<Int, Unit, Note>(){
        override fun doInBackground(vararg params: Int?): Note = dao.getById(params[0]!!)
    }
}