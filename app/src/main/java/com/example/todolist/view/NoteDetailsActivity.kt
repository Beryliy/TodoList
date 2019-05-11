package com.example.todolist.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.model.NoteRepository
import kotlinx.android.synthetic.main.activity_note_details.*

class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var repository: NoteRepository
    private lateinit var activityStrategy: DetailsActivityStrategy
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        repository = NoteRepository.getInstance(application)!!
        val currentNoteId = intent.getIntExtra("noteId", -1)
        if(currentNoteId == -1){
            invalidateOptionsMenu()
            activityStrategy = CreateActivityStrategy()
        }else{
            activityStrategy = UpdateActivityStrategy()
        }
        save_note_B.setOnClickListener({
            activityStrategy.save(repository.getNoteById(currentNoteId)!!, note_body_ET.text.toString(), repository)
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
