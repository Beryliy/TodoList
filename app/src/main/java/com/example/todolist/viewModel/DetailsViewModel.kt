package com.example.todolist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository
import com.example.todolist.model.strategy.CreateActivityStrategy
import com.example.todolist.model.strategy.DetailsActivityStrategy
import com.example.todolist.model.strategy.UpdateActivityStrategy

class DetailsViewModel(application: Application): AndroidViewModel(application) {
    private val repository: NoteRepository
    private lateinit var activityStrategy: DetailsActivityStrategy
    var note: LiveData<Note>? = null
    init{
        repository = NoteRepository.getInstance(application)!!
    }

    fun activityCreationHandler(id: Int){
        if(id != -1){
            note = repository.getNoteById(id)
            activityStrategy = UpdateActivityStrategy()
        }else{
            activityStrategy = CreateActivityStrategy()
        }
    }

    fun actionSave(body: String){
        Log.d("debug", "actionSave")
        activityStrategy.save(note, body, repository)
    }

    fun actionDelete(){
        repository.deleteNote(note?.value!!)
    }

    fun insertDummyData(){
        val note = Note(0,34534, "text")
        repository.insertNotes(note)
    }
}