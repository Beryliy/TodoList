package com.example.todolist.view

import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class UpdateActivityStrategy: DetailsActivityStrategy {
    override fun save(note: Note?, noteBody: String, repository: NoteRepository) {
        note!!.creationTime = System.currentTimeMillis()
        note!!.body = noteBody
        repository.updateNote(note)
    }

}