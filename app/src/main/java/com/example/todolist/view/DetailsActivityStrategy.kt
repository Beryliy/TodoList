package com.example.todolist.view

import androidx.lifecycle.LiveData
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

interface DetailsActivityStrategy {
    fun save(note: LiveData<Note>?, noteBody: String, repository: NoteRepository)
}