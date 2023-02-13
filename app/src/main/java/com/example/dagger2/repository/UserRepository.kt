package com.example.dagger2.repository

import com.example.dagger2.networking.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers() = flow { emit(apiService.getUsers()) }
}