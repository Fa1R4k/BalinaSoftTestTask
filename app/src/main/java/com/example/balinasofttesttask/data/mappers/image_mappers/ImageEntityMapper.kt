package com.example.balinasofttesttask.data.mappers.image_mappers

import com.example.balinasofttesttask.data.database.ImageEntity
import com.example.balinasofttesttask.data.models.image_models.ImageResponse
import com.example.balinasofttesttask.domain.model.ImageOutData
import javax.inject.Inject

class ImageEntityMapper @Inject constructor() {

    operator fun invoke(
        imageOut: ImageOutData
    ): ImageEntity = with(imageOut) {
        return ImageEntity(
            id = id,
            url = url,
            date = date,
            lat = lat,
            lng = lng
        )
    }

    operator fun invoke(
        imageOut: ImageResponse
    ): ImageEntity = with(imageOut) {
        return ImageEntity(
            id = id ?: -1,
            url = url.orEmpty(),
            date = date.toString(),
            lat = lat ?: -1.0,
            lng = lng ?: -1.0
        )
    }
}