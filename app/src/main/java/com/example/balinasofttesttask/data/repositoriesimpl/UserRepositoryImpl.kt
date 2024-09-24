package com.example.balinasofttesttask.data.repositoriesimpl

import com.example.balinasofttesttask.data.mappers.user_mappers.AuthDataResponseMapper
import com.example.balinasofttesttask.data.mappers.user_mappers.UserDtoMapper
import com.example.balinasofttesttask.data.network.service.AuthService
import com.example.balinasofttesttask.data.source.UserDataSource
import com.example.balinasofttesttask.domain.model.UserData
import com.example.balinasofttesttask.domain.repositories.UserRepository
import com.example.balinasofttesttask.utils.ApiRequestFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDtoMapper: UserDtoMapper,
    private val authDataMapper: AuthDataResponseMapper,
    private val userService: AuthService,
    private val apiRequestFlow: ApiRequestFlow
) : UserRepository {

    override fun registrationUser(user: UserData) = apiRequestFlow {
        authDataMapper(userService.signUp(userDtoMapper(user)))
    }

    override fun loginUser(user: UserData) = apiRequestFlow {
        authDataMapper(userService.signIn(userDtoMapper(user)))
    }
}