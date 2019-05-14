package com.example.todolist.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.example.todolist.R
import com.example.todolist.entities.Note
import com.example.todolist.model.pagination.NoteDiffUtilCallBack
import com.example.todolist.model.pagination.NotePagingAdapter
import com.example.todolist.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var noteListObserver: Observer<PagedList<Note>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        notes_list_RV.layoutManager = LinearLayoutManager(this)
        val adapter = NotePagingAdapter(NoteDiffUtilCallBack())
        noteListObserver = Observer<PagedList<Note>>(){
            if(it !== null){
                adapter.submitList(it)
                Log.d("debug", "adapter data changed")
            }
        }
        notes_list_RV.adapter = adapter
        mainViewModel.getObservableNotes().observe(this, noteListObserver)
        add_note_FAB.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NoteDetailsActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.sortOrderDESC -> {
                mainViewModel.setDecreaseOrder()
                mainViewModel.getObservableNotes().observe(this, noteListObserver)
                Log.d("debug", "desk in menu chosed")
                return true
            }
            R.id.sortOrderASC -> {
                mainViewModel.setIncreaseOrder()
                mainViewModel.getObservableNotes().observe(this, noteListObserver)
                Log.d("debug", "asc in menu chosed")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
