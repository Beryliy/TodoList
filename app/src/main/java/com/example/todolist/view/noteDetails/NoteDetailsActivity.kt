package com.example.todolist.view.noteDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
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
        currentNoteId = intent.getIntExtra("noteId", -1)
        detailsViewModel.activityCreationHandler(currentNoteId)
        val noteObserver = Observer<Note>{
            if(it !== null){
                note_body_ET.setText(it.body)
            }
        }
        detailsViewModel.note?.observe(this, noteObserver)
    }

    override fun onBackPressed() {
        if(!note_body_ET.text.isEmpty()){
            detailsViewModel.actionSave(note_body_ET.text.toString())
            Toast.makeText(this, resources.getText(R.string.saveInformMessege), Toast.LENGTH_SHORT).show()
        }
        super.onBackPressed()
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
                finish()
                return true
            }
            R.id.action_share -> {
                detailsViewModel.actionShare(this)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
