package com.elapp.mantuapp.presentation.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.elapp.mantuapp.data.entity.populatedata.PrePopulateData.Companion.getCategory
import com.elapp.mantuapp.data.entity.populatedata.PrePopulateData.Companion.getPriority
import com.elapp.mantuapp.data.local.dao.*
import com.elapp.mantuapp.data.local.database.MantooDatabase
import com.elapp.mantuapp.data.local.database.ioThread
import com.elapp.mantuapp.utils.ConstVal.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        priorityDaoProvider: Provider<PriorityDao>,
        categoryDaoProvider: Provider<CategoryDao>
    ): MantooDatabase {
        return Room.databaseBuilder(context, MantooDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        priorityDaoProvider.get().autoInsertPriority(getPriority())
                        categoryDaoProvider.get().prePopulateInsertCategory(getCategory())
                    }
                }
            })
            .build()
    }

    @Provides
    fun provideUserDao(database: MantooDatabase): UserDao = database.getUserDao()

    @Provides
    fun provideTaskDao(database: MantooDatabase): TaskDao = database.getTaskDao()

    @Provides
    fun provideCategoryDao(database: MantooDatabase): CategoryDao = database.getCategoryDao()

    @Provides
    fun providePriorityDao(database: MantooDatabase): PriorityDao = database.getPriorityDao()

    @Provides
    fun provideNoteDao(database: MantooDatabase): NoteDao = database.getNoteDao()

}