package com.example.todolist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class MainViewModel(application: Application): AndroidViewModel(application){
    private val observableNotes: LiveData<List<Note>>
    private val repository: NoteRepository
    init{
        repository = NoteRepository(application)
        observableNotes = repository.getAllNodes()
    }


    fun getObservableNotes() = observableNotes
}