package com.example.balinasofttesttask.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.balinasofttesttask.domain.repositories.ImageRepository
import javax.inject.Inject

class ImageLoaderFactory @Inject constructor(
    private val imageRepository: ImageRepository
) {

    private val PAGE_SIZE = 20

    fun getImages() =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource(imageRepository) },
            initialKey = 0
        ).flow
}