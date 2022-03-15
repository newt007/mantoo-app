package com.elapp.mantuapp.data.repository

import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.data.source.TaskDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDataSource: TaskDataSource) {

    suspend fun addNewTask(task: Task) = taskDataSource.addNewTask(task)

    suspend fun getAllTask() : List<Task> = taskDataSource.getAllTasks()

    suspend fun updateTask(task: Task) = taskDataSource.updateTask(task)

    suspend fun deleteTask(taskId: Int) = taskDataSource.deleteTask(taskId)

}