package com.example.todolist.view

import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class CreateActivityStrategy:  DetailsActivityStrategy{
    override fun save(note: Note, noteBody: String, repository: NoteRepository) {
        val note = Note()
        note.creationTime = System.currentTimeMillis()
        note.body = noteBody
        repository.insertNotes(note)
    }
}