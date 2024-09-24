package com.example.balinasofttesttask.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.balinasofttesttask.domain.repositories.CommentRepository
import com.example.balinasofttesttask.domain.repositories.ImageRepository
import javax.inject.Inject

class CommentLoaderFactory @Inject constructor(
    private val commentRepository: CommentRepository
) {

    private val PAGE_SIZE = 20

    fun getImages(id: Int) =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CommentPagingSource(commentRepository, id) },
            initialKey = 0
        ).flow
}