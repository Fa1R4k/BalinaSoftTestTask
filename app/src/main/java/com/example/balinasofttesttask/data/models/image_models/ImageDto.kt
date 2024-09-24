package com.example.balinasofttesttask.data.models.image_models

import com.squareup.moshi.Json

data class ImageDto(
    @Json(name = "ImageDtoIn") val imageDtoIn: ImageRequest? = null
)