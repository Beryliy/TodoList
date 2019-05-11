package com.example.todolist.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.todolist.R
import com.example.todolist.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var detailsViewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        val currentNoteId = intent.getIntExtra("noteId", -1)
        detailsViewModel.activityCreationHandler(currentNoteId)
        if(currentNoteId == -1) {
            invalidateOptionsMenu()
        }
        save_note_B.setOnClickListener({
            Log.d("debug", "onClick()")
            detailsViewModel.actionSave(note_body_ET.text.toString())
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_delete -> {
                return true
            }
            R.id.action_share -> {
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
