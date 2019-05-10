package com.example.todolist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class MainViewModel(val repository: NoteRepository): ViewModel(){
    private val observableNotes: LiveData<List<Note>>
    init{
        observableNotes = repository.getAllNodes()
    }


    fun getObservableNotes() = observableNotes
}