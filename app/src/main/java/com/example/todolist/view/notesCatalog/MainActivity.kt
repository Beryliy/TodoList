package com.example.todolist.view.notesCatalog


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
import com.example.todolist.view.notesCatalog.pagination.NoteDiffUtilCallBack
import com.example.todolist.view.notesCatalog.pagination.NotePagingAdapter
import com.example.todolist.view.noteDetails.NoteDetailsActivity
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
                if(!it.isEmpty()){
                    empty_view.visibility = View.GONE
                    notes_list_RV.visibility = View.VISIBLE
                }else{
                    notes_list_RV.visibility = View.GONE
                    empty_view.visibility = View.VISIBLE
                }
            }
        }
        notes_list_RV.adapter = adapter
        subscribeOnObservableNotes()
        add_note_FAB.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NoteDetailsActivity::class.java)
            startActivity(intent)
        })
        notes_SV.isActivated = false
        notes_SV.onActionViewExpanded()
        notes_SV.isIconified = false
        notes_SV.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.searchForContent(newText!!)
                subscribeOnObservableNotes()
                return true
            }
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
                subscribeOnObservableNotes()
                return true
            }
            R.id.sortOrderASC -> {
                mainViewModel.setIncreaseOrder()
                subscribeOnObservableNotes()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeOnObservableNotes(){
        mainViewModel.getObservableNotes().observe(this, noteListObserver)
    }
}
