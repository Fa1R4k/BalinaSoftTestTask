package com.example.balinasofttesttask.data.models.auth_models

import com.squareup.moshi.Json

data class AuthResponse(
    @Json(name = "status") val status: Int? = null,
    @Json(name = "data") val data: UserResponse? = null
)
