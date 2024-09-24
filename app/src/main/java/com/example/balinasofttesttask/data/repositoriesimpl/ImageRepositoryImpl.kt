package com.example.balinasofttesttask.data.repositoriesimpl

import com.example.balinasofttesttask.data.mappers.image_mappers.ImageOutDataMapper
import com.example.balinasofttesttask.data.mappers.image_mappers.ImageInResponseMapper
import com.example.balinasofttesttask.data.mappers.image_mappers.ImageMapper
import com.example.balinasofttesttask.data.mappers.image_mappers.ImageEntityMapper
import com.example.balinasofttesttask.data.network.service.ImageService
import com.example.balinasofttesttask.data.source.ImageDataBaseSource
import com.example.balinasofttesttask.domain.model.ImageInData
import com.example.balinasofttesttask.domain.model.ImageOutData
import com.example.balinasofttesttask.domain.repositories.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataBaseSource: ImageDataBaseSource,
    private val imageService: ImageService,
    private val imageOutDataMapper: ImageOutDataMapper,
    private val imageToEntityMapper: ImageEntityMapper,
    private val imageMapper: ImageMapper,
    private val imageInResponseMapper: ImageInResponseMapper,
) : ImageRepository {

    override suspend fun uploadImage(imageIn: ImageInData): ImageOutData =
        withContext(Dispatchers.IO) {
            val uploadImageResponse = imageInResponseMapper(imageIn)
            val response = imageService.uploadImage(uploadImageResponse)
            val imageResp = response.body()
            imageOutDataMapper(imageResp ?: throw IllegalStateException("ImageOutResponse is null"))
        }

    override suspend fun getImages(page: Int): List<ImageOutData> {
        val response = imageService.getImage(page)
        if (response.isSuccessful) {
            val imageResponse = response.body()
            val images = imageMapper(imageResponse?.data.orEmpty())
            return images
        } else {
            return imageDataBaseSource.getImagesFromDataBase().map { imageOutDataMapper(it) }
        }
    }

    override suspend fun updateDb(image: List<ImageOutData>) {
        withContext(Dispatchers.IO) {
            imageDataBaseSource.addPhotos(image.map { imageToEntityMapper.invoke(it) })
        }
    }

    override suspend fun deleteImage(imageId: Int): Result<Unit> {
        return try {
            val response = imageService.deleteImage(imageId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete image"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getImageFromDb(): List<ImageOutData> {
        return (imageDataBaseSource.getImagesFromDataBase().map { imageOutDataMapper(it) })
    }

    override suspend fun getImageById(id: Int): ImageOutData = withContext(Dispatchers.IO) {
        imageOutDataMapper(imageDataBaseSource.getImageById(id))
    }

    override fun getPagedPhotos() =
        imageDataBaseSource.getPagedPhotos().map { it -> it.map { imageOutDataMapper(it) } }


    override suspend fun deleteImageFromDb(imageOut: ImageOutData) =
        withContext(Dispatchers.IO) {
            imageDataBaseSource.deleteImageFromDb(imageToEntityMapper(imageOut))
        }

}