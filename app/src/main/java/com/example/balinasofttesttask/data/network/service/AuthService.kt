package com.example.balinasofttesttask.data.network.service

import com.example.balinasofttesttask.data.models.auth_models.AuthResponse
import com.example.balinasofttesttask.data.models.auth_models.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("account/signup")
    suspend fun signUp(@Body userDto: UserDto): Response<AuthResponse>

    @POST("account/signin")
    suspend fun signIn(@Body userDto: UserDto): Response<AuthResponse>
}