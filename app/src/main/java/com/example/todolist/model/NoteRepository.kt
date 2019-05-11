package com.example.todolist.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.todolist.entities.Note

class NoteRepository private constructor(application: Application) {
    private val dao: NoteDao
    private val observableNotes: LiveData<List<Note>>
    init{
        val database = NotesDatabase.getInstance(application.applicationContext)
        dao = database!!.noteDao()
        observableNotes = dao.getAll()
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

    fun getAllNotes() = observableNotes

    fun getNoteById(id: Int): Note? {
        if(id == -1){
            return null
        }else{
            var note: Note? = null
            val asyncTask = GetByIdAsyncTask(dao, note)
            asyncTask.execute(id)
            return note
        }
    }

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

    private class GetByIdAsyncTask(val dao: NoteDao, var note: Note?): AsyncTask<Int, Unit, Note>(){
        override fun doInBackground(vararg params: Int?): Note = dao.getById(params[0]!!)
        override fun onPostExecute(result: Note?) { note = result }
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