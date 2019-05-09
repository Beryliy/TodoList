package com.example.todolist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity
data class Note(
    @PrimaryKey val id: Int,
    @NotNull
    @ColumnInfo val creationTime : Int,
    @NotNull
    @ColumnInfo var body : String)