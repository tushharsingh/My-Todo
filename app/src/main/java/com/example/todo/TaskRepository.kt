package com.example.todo

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {
    val allTasks: Flow<List<Task>> = dao.getAllTasks()

    suspend fun addTask(task: Task) = dao.insertTask(task)
    suspend fun deleteTask(task: Task) = dao.deleteTask(task)
    suspend fun updateTask(task: Task) = dao.updateTask(task)
}
