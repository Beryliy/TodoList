package com.example.todolist.view

import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

interface DetailsActivityStrategy {
    fun save(note: Note, noteBody: String, repository: NoteRepository)
}