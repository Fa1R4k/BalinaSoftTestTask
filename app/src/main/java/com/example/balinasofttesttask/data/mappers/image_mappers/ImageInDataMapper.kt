package com.example.balinasofttesttask.data.mappers.image_mappers

import com.example.balinasofttesttask.data.models.image_models.ImageRequest
import com.example.balinasofttesttask.domain.model.ImageInData
import javax.inject.Inject

class ImageInDataMapper @Inject constructor() {

    operator fun invoke(
        response: ImageRequest
    ) : ImageInData = with(response) {
        return ImageInData(
            base64Image = base64Image.orEmpty(),
            date = date ?: 0,
            lat = lat ?: 0.0,
            lng = lng ?: 0.0
        )
    }
}