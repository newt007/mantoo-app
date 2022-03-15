package com.elapp.mantuapp.data.source

import com.elapp.mantuapp.data.entity.User
import com.elapp.mantuapp.data.local.dao.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(private val userDao: UserDao) {

    suspend fun addUser(name: String) {
        val userData = User(
            id = 0,
            name = name
        )
        userDao.saveDataForFirstTime(userData)
    }

    suspend fun getUserData(userId: Int): User = userDao.getUserData(userId)

    suspend fun updateUserData(user: User) {
        val userData = User(
            id = user.id,
            name = user.name
        )
        userDao.updateUserData(userData)
    }

}