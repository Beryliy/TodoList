package com.example.todolist.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import com.example.todolist.entities.Note
import com.example.todolist.global.App

class NoteRepository private constructor(application: Application) {
    private val dao: NoteDao
    private val observableNotes: LiveData<List<Note>>
    init{
        val database = App.instance?.getDatabase()
        dao = database!!.noteDao()
        observableNotes = dao.getAll()
        if(observableNotes.value == null){
            Log.d("debug", "observableNotes.value = null")
        }
    }

    fun insertNotes(note: Note){
        Log.d("debug", "insert note")
        //Log.d("debug", note.toString())
        val asyncTask = InsertAsyncTask(dao)
        asyncTask.execute(note)
    }

    fun updateNote(note: Note){
        UpdateAsyncTask(dao).execute(note)
    }

    fun deleteNote(note: Note){
        DeleteAsyncTask(dao).execute(note)
    }

    fun getAllNotes() = observableNotes

    fun getNoteById(id: Int): LiveData<Note>? {
        if(id == -1){
            return null
        }else{
            return dao.getById(id)
        }
    }

    private class InsertAsyncTask(val dao: NoteDao): AsyncTask<Note, Unit, Unit>(){
        override fun doInBackground(vararg params: Note) {
            Log.d("debug", params[0].toString())
            dao.insert(params[0])
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

    private class GetByIdAsyncTask(val dao: NoteDao): AsyncTask<Int, Unit, LiveData<Note>>(){
        override fun doInBackground(vararg params: Int?): LiveData<Note> = dao.getById(params[0]!!)
    }
    companion object{
        private var noteRepository: NoteRepository? = null
        fun getInstance(application: Application): NoteRepository? {
            if (noteRepository == null) {
                synchronized(NoteRepository::class) {
                    noteRepository = NoteRepository(application)
                }
            }
            return noteRepository
        }
    }
}