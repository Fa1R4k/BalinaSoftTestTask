package com.example.balinasofttesttask.data.models.image_models

import com.squareup.moshi.Json

data class ImageUploadResponse(
    @Json(name = "status") val status: Int? = null,
    @Json(name = "data") val data: ImageResponse? = null,
)