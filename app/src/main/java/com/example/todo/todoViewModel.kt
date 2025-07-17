package com.example.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class todoViewModel:ViewModel() {
private var _todoList=MutableLiveData<List<Task>>()
    val todoList:LiveData<List<Task>> = _todoList
    fun getAllTodo(){
        _todoList.value=TodoManager.getAllTodo().reversed()
    }
    fun addAllTodo(title:String){
      TodoManager.addAllTodo(title)
        getAllTodo()
    }
    fun delTodo( id:Int){
      TodoManager.delTodo(id)
        getAllTodo()

    }


}