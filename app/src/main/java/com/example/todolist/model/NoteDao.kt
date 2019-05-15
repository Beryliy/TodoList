package com.example.todolist.model


import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.example.todolist.entities.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note ORDER BY creationTime DESC")
    fun getNotesNewFirst(): DataSource.Factory<Int, Note>

    @Query("SELECT * FROM note ORDER BY creationTime ASC")
    fun getNotesOldFirst(): DataSource.Factory<Int, Note>

    @Query("SELECT * FROM note WHERE body LIKE :query")
    fun getByContent(query: String): DataSource.Factory<Int, Note>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Int): LiveData<Note>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)
}