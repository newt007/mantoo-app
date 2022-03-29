package com.elapp.mantuapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elapp.mantuapp.data.entity.*
import com.elapp.mantuapp.data.local.dao.*

@Database(
    entities = [User::class, Task::class, Category::class, Priority::class, Note::class],
    version = 1,
    exportSchema = false
)
abstract class MantooDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getTaskDao(): TaskDao

    abstract fun getCategoryDao(): CategoryDao

    abstract fun getPriorityDao(): PriorityDao

    abstract fun getNoteDao(): NoteDao

}