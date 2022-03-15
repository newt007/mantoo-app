package com.elapp.mantuapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.data.entity.Priority
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.data.entity.User
import com.elapp.mantuapp.data.local.dao.CategoryDao
import com.elapp.mantuapp.data.local.dao.PriorityDao
import com.elapp.mantuapp.data.local.dao.TaskDao
import com.elapp.mantuapp.data.local.dao.UserDao

@Database(
    entities = [User::class, Task::class, Category::class, Priority::class],
    version = 1,
    exportSchema = false
)
abstract class MantooDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getTaskDao(): TaskDao

    abstract fun getCategoryDao(): CategoryDao

    abstract fun getPriorityDao(): PriorityDao

}