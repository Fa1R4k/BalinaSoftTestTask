package com.example.balinasofttesttask.domain.repositories

import com.example.balinasofttesttask.domain.model.AuthData
import com.example.balinasofttesttask.domain.model.UserData
import com.example.balinasofttesttask.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun registrationUser(user: UserData): Flow<ApiResponse<AuthData>>

    fun loginUser(user: UserData): Flow<ApiResponse<AuthData>>
}