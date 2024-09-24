package com.example.balinasofttesttask.domain.model

data class ImageInData(
    val base64Image: String,
    val date: Long,
    val lat: Double,
    val lng: Double
)