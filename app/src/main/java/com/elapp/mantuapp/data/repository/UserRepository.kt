package com.elapp.mantuapp.data.repository

import com.elapp.mantuapp.data.entity.User
import com.elapp.mantuapp.data.source.UserDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDataSource: UserDataSource) {

    suspend fun saveDataForFirstTime(name: String) = userDataSource.addUser(name)

    suspend fun getUserData(userId: Int): User = userDataSource.getUserData(userId)

    suspend fun updateUserData(user: User) = userDataSource.updateUserData(user)
}