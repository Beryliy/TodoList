package com.example.todolist.model.pagination

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.todolist.R
import com.example.todolist.entities.Note
import com.example.todolist.view.NoteDetailsActivity
import java.text.SimpleDateFormat

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val changeDateTV: TextView
    val changeTimeTV: TextView
    val noteBodyTV: TextView
    init{
        changeDateTV = view.findViewById(R.id.change_date_TV)
        changeTimeTV = view.findViewById(R.id.change_time_TV)
        noteBodyTV = view.findViewById(R.id.note_body_TV)
    }
    fun bind(note: Note){
        changeDateTV.setText(castIntoDate(note.creationTime))
        changeTimeTV.setText(castIntoTime(note.creationTime))
        noteBodyTV.setText(note.body)
        itemView.setOnClickListener({
            val intent = Intent(it.context, NoteDetailsActivity::class.java)
            intent.putExtra("noteId", note.id)
            startActivity(it.context, intent, null)
        })
    }

    fun castIntoDate(milis: Long): String{
        val dateFormat = SimpleDateFormat("dd.MM.yy")
        return dateFormat.format(milis)
    }

    fun castIntoTime(milis:Long): String{
        val timeFormat = SimpleDateFormat("HH.mm")
        return timeFormat.format(milis)
    }
}