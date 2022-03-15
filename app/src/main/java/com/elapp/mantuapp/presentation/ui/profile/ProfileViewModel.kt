package com.elapp.mantuapp.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elapp.mantuapp.data.entity.User
import com.elapp.mantuapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private var userRepository: UserRepository): ViewModel() {

    fun saveUserDataForFirstTime(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.saveDataForFirstTime(name)
        }
    }

    fun getUserData(userId: Int): LiveData<User> {
        val user = MutableLiveData<User>()
        viewModelScope.launch(Dispatchers.IO) {
            user.postValue(userRepository.getUserData(userId))
        }
        return user
    }

}