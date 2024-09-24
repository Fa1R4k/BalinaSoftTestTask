package com.example.balinasofttesttask.data.models.auth_models

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String,
)
