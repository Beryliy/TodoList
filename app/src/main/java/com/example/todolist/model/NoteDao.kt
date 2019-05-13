package com.example.todolist.model


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.todolist.entities.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Int): LiveData<Note>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}