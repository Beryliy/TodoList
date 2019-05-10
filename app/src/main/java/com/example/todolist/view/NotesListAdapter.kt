package com.example.todolist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.todolist.R
import com.example.todolist.entities.Note
import java.text.SimpleDateFormat

class NotesListAdapter(): RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {
    private lateinit var notes: List<Note>
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val changeDateTV: TextView
        val changeTimeTV: TextView
        val noteBodyTV: TextView
        init{
            changeDateTV = view.findViewById(R.id.change_date_TV)
            changeTimeTV = view.findViewById(R.id.change_time_TV)
            noteBodyTV = view.findViewById(R.id.note_body_TV)
            //TODO: add OnClickListener view.setOnClickListener()
        }
    }

    fun setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    /*fun add(note: Note){
        notes!! += note
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.note_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val note = notes[position]
        holder?.changeDateTV?.setText(castIntoDate(note.creationTime))
        holder?.changeTimeTV?.setText(castIntoTime(note.creationTime))
        holder?.noteBodyTV?.setText(note.body)
    }

    override fun getItemCount() = notes.size

    fun castIntoDate(milis: Long): String{
        val dateFormat = SimpleDateFormat("dd.MM.yy")
        return dateFormat.format(milis)
    }

    fun castIntoTime(milis:Long): String{
        val timeFormat = SimpleDateFormat("HH.mm")
        return timeFormat.format(milis)
    }
}