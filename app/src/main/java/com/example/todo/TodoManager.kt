package com.example.todo

import java.time.Instant
import java.util.Date

object TodoManager {
    private val todoList= mutableListOf<Task>()
    fun getAllTodo():List<Task>{
           return todoList
    }
    fun addAllTodo(title:String){
          todoList.add(Task(System.currentTimeMillis().toInt(),title,Date.from(Instant.now())))

    }
    fun delTodo( id:Int){
          todoList.removeIf {
              it.id==id
          }

    }


}