package com.example.balinasofttesttask.data.source

import com.example.balinasofttesttask.data.database.ImageDao
import com.example.balinasofttesttask.data.database.ImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageDataBaseSource @Inject constructor(
    private val imageDao: ImageDao
) {

    suspend fun getImagesFromDataBase(): List<ImageEntity> = imageDao.getImagesFromDataBase()

    suspend fun getImageById(id: Int): ImageEntity = imageDao.getImageById(id)

    fun getPagedPhotos(): Flow<List<ImageEntity>> = imageDao.getPagedPhotos()

    suspend fun deleteImageFromDb(imageEntity: ImageEntity) = imageDao.deleteImageFromDb(imageEntity)

    suspend fun addPhotos(imageEntityList: List<ImageEntity>) = imageDao.addPhotos(imageEntityList)

}