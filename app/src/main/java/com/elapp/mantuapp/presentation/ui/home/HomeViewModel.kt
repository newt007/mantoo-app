package com.elapp.mantuapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.data.entity.User
import com.elapp.mantuapp.data.repository.TaskRepository
import com.elapp.mantuapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    fun getAllTask(): LiveData<List<Task>> {
        val task = MutableLiveData<List<Task>>()
        viewModelScope.launch(Dispatchers.IO) {
            task.postValue(taskRepository.getAllTask())
        }
        return task
    }

    fun getUserData(userId: Int): LiveData<User> {
        val user = MutableLiveData<User>()
        viewModelScope.launch(Dispatchers.IO) {
            user.postValue(userRepository.getUserData(userId))
        }
        return user
    }

}