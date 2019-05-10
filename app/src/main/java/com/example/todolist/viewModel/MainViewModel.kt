package com.example.todolist.viewModel

import android.arch.lifecycle.LiveData

import android.arch.lifecycle.ViewModel
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class MainViewModel(val repository: NoteRepository): ViewModel() {


    fun loadNotes(): LiveData<List<Note>> = repository.getAllNodes()

}