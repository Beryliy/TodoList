package com.example.todolist.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.todolist.R
import com.example.todolist.entities.Note
import com.example.todolist.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_note_details.*


class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var detailsViewModel: DetailsViewModel
    private var currentNoteId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        detailsViewModel.activityCreationHandler(currentNoteId)
        val noteObserver = Observer<Note>{
            if(it !== null){
                note_body_ET.setText(it.body)
            }
        }
        detailsViewModel.note?.observe(this, noteObserver)
    }

    override fun onStop() {
        super.onStop()
        detailsViewModel.actionSave(note_body_ET.text.toString())
        Toast.makeText(this, resources.getText(R.string.saveInformMessege), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        if(currentNoteId == -1) {
            menu?.findItem(R.id.action_delete)?.isVisible = false
            menu?.findItem(R.id.action_share)?.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_delete -> {
                detailsViewModel.actionDelete()
                return true
            }
            R.id.action_share -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
