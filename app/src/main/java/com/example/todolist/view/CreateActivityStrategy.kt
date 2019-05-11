package com.example.todolist.view

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class CreateActivityStrategy:  DetailsActivityStrategy{
    override fun save(note: LiveData<Note>?, noteBody: String, repository: NoteRepository) {
        Log.d("debug", "strtegy save()")
        val currentNote = Note()
        currentNote.creationTime = System.currentTimeMillis()
        currentNote.body = noteBody
        repository.insertNotes(currentNote)
    }
}