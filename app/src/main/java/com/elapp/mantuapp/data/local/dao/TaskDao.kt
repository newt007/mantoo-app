package com.elapp.mantuapp.data.local.dao

import androidx.room.*
import com.elapp.mantuapp.data.entity.Task

@Dao
interface TaskDao {

    @Insert
    suspend fun insertNewTask(task: Task)

    @Query("SELECT * FROM tbl_task WHERE task_date=:date order BY task_time")
    suspend fun getTaskListByDate(date: String): List<Task>

    @Query("SELECT * FROM  tbl_task")
    suspend fun getAllTask(): List<Task>

    @Update
    suspend fun updateTask(task: Task)

    @Query("DELETE FROM tbl_task WHERE task_id = :taskId ")
    suspend fun deleteTask(taskId: Int)

}