package com.example.todolist.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todolist.R
import com.example.todolist.entities.Note
import com.example.todolist.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        notes_list_RV.layoutManager = LinearLayoutManager(this)
        var adapter = NotesListAdapter()
        adapter.setNotes(mainViewModel.getObservableNotes().value!!)
        notes_list_RV.adapter = adapter
        val noteObserver = Observer<List<Note>>(){
            adapter.setNotes(it)
        }
        mainViewModel.getObservableNotes().observe(this, noteObserver)
    }
}
