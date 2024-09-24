package com.example.balinasofttesttask.data.mappers.image_mappers

import com.example.balinasofttesttask.data.database.ImageEntity
import com.example.balinasofttesttask.data.models.image_models.ImageUploadResponse
import com.example.balinasofttesttask.domain.model.ImageOutData
import javax.inject.Inject

class ImageOutDataMapper @Inject constructor() {

    operator fun invoke(
        imageEntity: ImageEntity
    ): ImageOutData = with(imageEntity) {
        return ImageOutData(
            id = id,
            url = url,
            date = date,
            lat = lat,
            lng = lng
        )
    }

    operator fun invoke(
        response: ImageUploadResponse
    ): ImageOutData {
        val image = response.data
        return ImageOutData(
            id = image?.id ?: 0,
            url = image?.url.orEmpty(),
            date = image?.date.toString().orEmpty(),
            lat = image?.lat ?: 0.0,
            lng = image?.lng ?: 0.0
        )
    }
}