package com.elapp.mantuapp.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
    val taskCategory: Long,
    @ColumnInfo(name = "task_priority")
    val taskPriority: Long,
    @ColumnInfo(name = "task_description")
    val taskDescription: String,
    @ColumnInfo(name = "task_status")
    val taskStatus: Int = 0,
    @ColumnInfo(name = "task_accomplished_date")
    val taskAccomplishedDate: String? = null
) : Parcelable
