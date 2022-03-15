package com.elapp.mantuapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.elapp.mantuapp.data.entity.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun addNewCategory(category: Category)

    @Query("SELECT * FROM tbl_category")
    suspend fun getAllCategory(): List<Category>

    @Update
    suspend fun updateCategory(category: Category)

    @Query("DELETE FROM tbl_category WHERE category_id = :categoryId")
    suspend fun deleteCategory(categoryId: Int)

}