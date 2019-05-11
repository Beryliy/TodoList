package com.example.todolist.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository
import com.example.todolist.view.CreateActivityStrategy
import com.example.todolist.view.DetailsActivityStrategy
import com.example.todolist.view.UpdateActivityStrategy

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
}