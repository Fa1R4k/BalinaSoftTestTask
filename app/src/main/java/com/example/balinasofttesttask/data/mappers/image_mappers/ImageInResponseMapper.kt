package com.example.balinasofttesttask.data.mappers.image_mappers

import com.example.balinasofttesttask.data.models.image_models.ImageRequest
import com.example.balinasofttesttask.domain.model.ImageInData
import javax.inject.Inject

class ImageInResponseMapper @Inject constructor() {

    operator fun invoke(
        imageIn: ImageInData
    ): ImageRequest = with(imageIn) {
        return ImageRequest(
            base64Image = base64Image,
            date = date,
            lat = lat,
            lng = lng
        )
    }
}