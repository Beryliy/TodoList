package com.example.todolist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.Toast
import com.example.todolist.R
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

    fun actionShare(context: Context){
        val intent = Intent().apply {
            Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, note?.value!!.body)
            type = "text/plain"
        }
        val activities: List<ResolveInfo> = context.packageManager.queryIntentActivities(intent,
            PackageManager.MATCH_DEFAULT_ONLY)
        if(!activities.isEmpty()){
            startActivity(context, intent, null)
        }else{
            Toast.makeText(context, context.resources.getText(R.string.unableSharigMessege), Toast.LENGTH_SHORT).show()
        }
    }
}