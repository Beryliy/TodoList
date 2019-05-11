package com.example.todolist.view

import androidx.lifecycle.LiveData
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class UpdateActivityStrategy: DetailsActivityStrategy {
    override fun save(note: LiveData<Note>?, noteBody: String, repository: NoteRepository) {
        val currentNote = note!!.value
        currentNote!!.creationTime = System.currentTimeMillis()
        currentNote.body = noteBody
        repository.updateNote(currentNote)
    }

}