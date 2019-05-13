package com.example.todolist.model.strategy


import android.arch.lifecycle.LiveData
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

interface DetailsActivityStrategy {
    fun save(note: LiveData<Note>?, noteBody: String, repository: NoteRepository)
}