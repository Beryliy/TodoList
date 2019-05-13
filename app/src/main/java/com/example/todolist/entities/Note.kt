package com.example.todolist.entities


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @NotNull
    @ColumnInfo var creationTime : Long,
    @NotNull
    @ColumnInfo var body : String){
    constructor():this(0,0, "")

    override fun toString(): String {
        return "Id = ${id.toString()}\nbody: $body\ntime: ${creationTime.toString()}"
    }
}