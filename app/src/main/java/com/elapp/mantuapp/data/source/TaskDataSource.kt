package com.elapp.mantuapp.data.source

import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.data.local.dao.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDataSource @Inject constructor(private val taskDao: TaskDao) {

    suspend fun addNewTask(task: Task) {
        val newTask = Task(
            taskTitle = task.taskTitle,
            taskDate = task.taskDate,
            taskTime = task.taskTime,
            taskCategory = task.taskCategory,
            taskDescription = task.taskDescription
        )
        taskDao.insertNewTask(task)
    }

    suspend fun getAllTasks(): List<Task> = taskDao.getAllTask()

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(taskId: Int) = taskDao.deleteTask(taskId)

}