package com.example.balinasofttesttask.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.balinasofttesttask.domain.model.ImageOutData
import com.example.balinasofttesttask.domain.repositories.ImageRepository

class ImagePagingSource(
    private val imageRepository: ImageRepository
) : PagingSource<Int, ImageOutData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageOutData> {
        return try {
            val pageIndex = params.key ?: 1
            val images = imageRepository.getImages(pageIndex)
            return LoadResult.Page(
                data = images,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (images.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageOutData>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}