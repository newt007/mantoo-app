package com.elapp.mantuapp.data.repository

import com.elapp.mantuapp.data.entity.Priority
import com.elapp.mantuapp.data.source.PriorityDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PriorityRepository @Inject constructor(private val priorityDataSource: PriorityDataSource) {

    suspend fun getAllPriority(): List<Priority> = priorityDataSource.getAllPriority()

}