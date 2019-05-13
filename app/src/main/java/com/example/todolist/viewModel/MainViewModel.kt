package com.example.todolist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class MainViewModel(application: Application): AndroidViewModel(application){
    private val observableNotes: LiveData<List<Note>>
    private val repository: NoteRepository?
    init{
        repository = NoteRepository.getInstance(application)
        observableNotes = repository!!.getAllNotes()
    }
    fun getObservableNotes() = observableNotes
}