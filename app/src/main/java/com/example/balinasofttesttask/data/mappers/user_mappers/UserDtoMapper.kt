package com.example.balinasofttesttask.data.mappers.user_mappers

import com.example.balinasofttesttask.data.models.auth_models.UserDto
import com.example.balinasofttesttask.domain.model.UserData
import javax.inject.Inject

class UserDtoMapper @Inject constructor() {
    operator fun invoke(user: UserData): UserDto = with(user) {
        UserDto(
            login = login,
            password = password
        )
    }
}