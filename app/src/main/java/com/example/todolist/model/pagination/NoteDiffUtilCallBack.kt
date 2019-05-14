package com.example.todolist.model.pagination

import android.support.v7.util.DiffUtil
import com.example.todolist.entities.Note

class NoteDiffUtilCallBack: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldNote: Note, newNote: Note) = oldNote.id == newNote.id

    override fun areContentsTheSame(oldNote: Note, newNote: Note) =
            oldNote.body.equals(newNote.body) && oldNote.creationTime == newNote.creationTime
}