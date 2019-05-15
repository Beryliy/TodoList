package com.example.todolist.view.notesCatalog.pagination

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.todolist.R
import com.example.todolist.entities.Note

class NotePagingAdapter(val diffUtilCallback: DiffUtil.ItemCallback<Note>): PagedListAdapter<Note, NoteViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_recyclerview_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}