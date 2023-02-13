package com.example.dagger2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun getGithubUsers(): StateFlow<UserResource> {
        val stateFlow = MutableStateFlow<UserResource>(UserResource.Loading)
        viewModelScope.launch {
            val flow = userRepository.getUsers()
            flow.catch {
                stateFlow.emit(UserResource.Error(it.message ?: ""))
            }.collect {
                if(it.isSuccessful) {
                    stateFlow.emit(UserResource.Success(it.body()))
                } else {
                    stateFlow.emit(UserResource.Error(it.message()))
                }
            }
        }
        return stateFlow
    }
}