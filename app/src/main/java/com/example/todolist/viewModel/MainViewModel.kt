package com.example.todolist.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.arch.persistence.room.Database
import android.util.Log

import com.example.todolist.entities.Note
import com.example.todolist.model.NoteRepository

class MainViewModel(application: Application): AndroidViewModel(application){
    private var sortOrder: Int
    private val listConfig: PagedList.Config
    private var observableNotes: LiveData<PagedList<Note>>
    private val repository: NoteRepository?
    init{
        listConfig = PagedList.Config.Builder().
            setEnablePlaceholders(false).
            setPageSize(20).
            build()
        repository = NoteRepository.getInstance(application)
        sortOrder = NEW_FIRST
        observableNotes = LivePagedListBuilder<Int, Note>(repository?.getSourceFactory()!!, listConfig).build()

    }
    fun setDecreaseOrder(){
        if(sortOrder != NEW_FIRST){
        Log.d("debug", "")
            repository?.selectFactoryNewFirst()
            observableNotes = LivePagedListBuilder<Int, Note>(repository?.getSourceFactory()!!, listConfig).build()
            sortOrder = NEW_FIRST
        }
    }

    fun setIncreaseOrder(){
        if(sortOrder != OLD_FIRST){
        Log.d("debug", "model sort = old first")
            repository?.selectFactoryOldFirst()
            observableNotes = LivePagedListBuilder<Int, Note>(repository?.getSourceFactory()!!, listConfig).build()
            sortOrder = OLD_FIRST
        }
    }

    fun searchForContent(query: String){
        if(query.isEmpty()){
            when(sortOrder){
                NEW_FIRST -> {
                    repository?.selectFactoryNewFirst()
                    observableNotes = LivePagedListBuilder<Int, Note>(repository?.getSourceFactory()!!, listConfig).build()
                }
                OLD_FIRST -> {
                    repository?.selectFactoryOldFirst()
                    observableNotes = LivePagedListBuilder<Int, Note>(repository?.getSourceFactory()!!, listConfig).build()
                }
            }
        }else{
            repository?.selectFactoryWithQuery(query)
            observableNotes = LivePagedListBuilder<Int, Note>(repository?.getSourceFactory()!!, listConfig).build()
        }
    }

    fun getObservableNotes() = observableNotes

    companion object{
        const val NEW_FIRST = 1
        const val OLD_FIRST = 2
    }
}