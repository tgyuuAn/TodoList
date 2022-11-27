package com.example.todolist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoEntity ORDER BY importance ASC")
    fun getAllToDo() : List<ToDoEntity>

    @Insert
    fun insertToDo(todo : ToDoEntity)

    @Delete
    fun deleteToDo(todo : ToDoEntity)

}