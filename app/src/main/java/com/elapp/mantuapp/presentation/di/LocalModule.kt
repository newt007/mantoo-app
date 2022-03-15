package com.elapp.mantuapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.elapp.mantuapp.data.local.dao.TaskDao
import com.elapp.mantuapp.data.local.dao.UserDao
import com.elapp.mantuapp.data.local.database.MantooDatabase
import com.elapp.mantuapp.utils.ConstVal.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MantooDatabase {
        return Room.databaseBuilder(context, MantooDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: MantooDatabase): UserDao = database.getUserDao()

    @Provides
    fun provideTaskDao(database: MantooDatabase): TaskDao = database.getTaskDao()

}