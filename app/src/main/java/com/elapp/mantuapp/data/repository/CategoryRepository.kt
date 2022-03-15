package com.elapp.mantuapp.data.repository

import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.data.source.CategoryDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(private val categoryDataSource: CategoryDataSource) {

    suspend fun insertNewCategory(category: Category) = categoryDataSource.insertNewCategory(category)

    suspend fun getAllCategory(): List<Category> = categoryDataSource.getAllCategory()

    suspend fun updateCategory(category: Category) = categoryDataSource.updateCategory(category)

    suspend fun deleteCategory(categoryId: Int) = categoryDataSource.deleteCategory(categoryId)

}