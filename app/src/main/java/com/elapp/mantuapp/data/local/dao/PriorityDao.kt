package com.elapp.mantuapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.elapp.mantuapp.data.entity.Priority

@Dao
interface PriorityDao {

    @Insert
    fun autoInsertPriority(priority: List<Priority>)

    @Query("SELECT * FROM tbl_priority")
    suspend fun getPriority(): List<Priority>

}