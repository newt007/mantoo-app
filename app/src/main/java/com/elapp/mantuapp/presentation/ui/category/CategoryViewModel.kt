package com.elapp.mantuapp.presentation.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository): ViewModel() {

    fun getAllCategory(): LiveData<List<Category>> {
        val category = MutableLiveData<List<Category>>()
        viewModelScope.launch(Dispatchers.IO) {
            category.postValue(categoryRepository.getAllCategory())
        }
        return category
    }

    fun addNewCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.insertNewCategory(category)
        }
    }

}