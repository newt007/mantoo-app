package com.elapp.mantuapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_task")
data class Task(
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    val idTask: Int = 0,
    @ColumnInfo(name = "task_title")
    val taskTitle: String,
    @ColumnInfo(name = "task_date")
    val taskDate: String,
    @ColumnInfo(name = "task_time")
    val taskTime: String,
    @ColumnInfo(name = "task_category")
    val taskCategory: Int,
    @ColumnInfo(name = "task_description")
    val taskDescription: String
)
