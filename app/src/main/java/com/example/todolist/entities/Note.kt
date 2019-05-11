package com.example.todolist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity
data class Note(
    @PrimaryKey (autoGenerate = true) var id: Int?,
    @NotNull
    @ColumnInfo var creationTime : Long,
    @NotNull
    @ColumnInfo var body : String){
    constructor():this(null, 0, "")

    override fun toString(): String {
        return "Id = ${id.toString()}\nbody: $body\ntime: ${creationTime.toString()}"
    }
}