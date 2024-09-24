package com.example.balinasofttesttask.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM image_table")
    suspend fun getImagesFromDataBase(): List<ImageEntity>

    @Query("SELECT * FROM image_table WHERE id = :id")
    suspend fun getImageById(id: Int): ImageEntity

    @Insert
    suspend fun addPhotos(imageEntityList: List<ImageEntity>)

    @Query("SELECT * FROM image_table ORDER BY id")
    fun getPagedPhotos(): Flow<List<ImageEntity>>

    @Delete
    suspend fun deleteImageFromDb(imageEntity: ImageEntity)
}