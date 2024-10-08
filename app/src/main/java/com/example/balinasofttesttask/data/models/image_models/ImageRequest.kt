package com.example.balinasofttesttask.data.models.image_models

import com.squareup.moshi.Json

data class ImageRequest(
    @Json(name = "base64Image") val base64Image: String? = null,
    @Json(name = "date") val date: Long? = null,
    @Json(name = "lat") val lat: Double? = null,
    @Json(name = "lng") val lng: Double? = null
)