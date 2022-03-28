package com.elapp.mantuapp.data.source

import com.elapp.mantuapp.data.entity.Priority
import com.elapp.mantuapp.data.local.dao.PriorityDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PriorityDataSource @Inject constructor(private val priorityDao: PriorityDao) {

    suspend fun getAllPriority(): List<Priority> = priorityDao.getPriority()

}