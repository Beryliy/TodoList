package com.example.todolist.model

import androidx.room.*
import com.example.todolist.entities.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Int): Note

    @Insert
    fun insertAll(vararg notes: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}