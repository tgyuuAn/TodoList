package com.example.todolist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true) val Id : Int? = null,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "importance") val Importance : Int
)