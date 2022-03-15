package com.elapp.mantuapp.data.source

import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.data.local.dao.CategoryDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryDataSource @Inject constructor(private val categoryDao: CategoryDao) {

    suspend fun insertNewCategory(category: Category) {
        val dataCategory = Category(
            categoryName = category.categoryName
        )
        categoryDao.addNewCategory(dataCategory)
    }

    suspend fun getAllCategory(): List<Category> = categoryDao.getAllCategory()

    suspend fun deleteCategory(categoryId: Int) = categoryDao.deleteCategory(categoryId)

    suspend fun updateCategory(category: Category) = categoryDao.updateCategory(category)

}