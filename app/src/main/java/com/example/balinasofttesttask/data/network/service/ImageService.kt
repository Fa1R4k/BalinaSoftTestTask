package com.example.balinasofttesttask.data.network.service

import com.example.balinasofttesttask.data.models.image_models.ImageRequest
import com.example.balinasofttesttask.data.models.image_models.ImageUploadResponse
import com.example.balinasofttesttask.data.models.image_models.ImagesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageService {
    @GET("image")
    suspend fun getImage(@Query("page") page: Int): Response<ImagesResponse>

    @POST("image")
    suspend fun uploadImage(@Body imageRequest: ImageRequest): Response<ImageUploadResponse>

    @DELETE("image/{imageId}")
    suspend fun deleteImage(@Path("imageId") imageId: Int): Response<ImageUploadResponse>
}