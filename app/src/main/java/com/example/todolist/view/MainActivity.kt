package com.example.todolist.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.example.todolist.R
import com.example.todolist.entities.Note
import com.example.todolist.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        notes_list_RV.layoutManager = LinearLayoutManager(this)
        val adapter = NotesListAdapter()
        val noteListObserver = Observer<List<Note>>(){
            if(it !== null){
                adapter.setNotes(it)
            }
        }
        notes_list_RV.adapter = adapter
        mainViewModel.getObservableNotes().observe(this, noteListObserver)
        add_note_FAB.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NoteDetailsActivity::class.java)
            startActivity(intent)
        })
    }
}
