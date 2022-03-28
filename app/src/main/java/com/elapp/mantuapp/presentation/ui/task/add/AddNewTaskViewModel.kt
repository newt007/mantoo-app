package com.elapp.mantuapp.presentation.ui.task.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.data.entity.Priority
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.data.repository.CategoryRepository
import com.elapp.mantuapp.data.repository.PriorityRepository
import com.elapp.mantuapp.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository,
    private val priorityRepository: PriorityRepository
): ViewModel() {

    fun getAllCategory(): LiveData<List<Category>> {
        val category = MutableLiveData<List<Category>>()
        viewModelScope.launch(Dispatchers.IO) {
            category.postValue(categoryRepository.getAllCategory())
        }
        return category
    }

    fun getAllPriority(): LiveData<List<Priority>> {
        val priority = MutableLiveData<List<Priority>>()
        viewModelScope.launch(Dispatchers.IO) {
            priority.postValue(priorityRepository.getAllPriority())
        }
        return priority
    }

    fun addNewTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO){
            taskRepository.addNewTask(task)
        }
    }

}