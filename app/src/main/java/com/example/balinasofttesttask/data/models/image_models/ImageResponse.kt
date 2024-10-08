package com.example.balinasofttesttask.data.models.image_models

import com.squareup.moshi.Json

data class ImageResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "date") val date: Long? = null,
    @Json(name = "lat") val lat: Double? = null,
    @Json(name = "lng") val lng: Double? = null
)
