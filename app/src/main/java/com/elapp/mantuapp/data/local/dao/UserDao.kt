package com.elapp.mantuapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.elapp.mantuapp.data.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun saveDataForFirstTime(user: User)

    @Query("SELECT * FROM tbl_user WHERE id=:id")
    suspend fun getUserData(id: Int): User

    @Update
    suspend fun updateUserData(user: User)

}