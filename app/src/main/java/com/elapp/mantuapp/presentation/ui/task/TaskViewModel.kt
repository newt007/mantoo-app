package com.elapp.mantuapp.presentation.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    fun deleteTask(taskId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(taskId)
        }
    }

    fun getTaskByDate(date: String): LiveData<List<Task>> {
        val taskList = MutableLiveData<List<Task>>()
        viewModelScope.launch(Dispatchers.IO) {
            taskList.postValue(taskRepository.getTaskListByDate(date))
        }
        return taskList
    }

}