package com.elapp.mantuapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_priority")
data class Priority(
    @ColumnInfo(name = "priority_id")
    @PrimaryKey(autoGenerate = true)
    val priorityId: Long = 0,
    @ColumnInfo(name = "priority_name")
    val priorityName: String,
    @ColumnInfo(name = "priority_desc")
    val priorityDesc: String,
    @ColumnInfo(name = "priority_color")
    val priorityColor: Int
)
