package com.example.balinasofttesttask.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.balinasofttesttask.data.source.ImageLoaderFactory
import com.example.balinasofttesttask.domain.model.ImageOutData
import com.example.balinasofttesttask.domain.repositories.ImageRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val imageLoaderFactory: ImageLoaderFactory
) : ViewModel() {

    private val _imageFlow: MutableSharedFlow<PagingData<ImageOutData>> = MutableSharedFlow()
    val imageFlow: SharedFlow<PagingData<ImageOutData>> get() = _imageFlow

    private val _deleteImageLiveData = MutableLiveData<Boolean>()
    val deleteImageLiveData: LiveData<Boolean> get() = _deleteImageLiveData

    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }

    fun updateList() {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            imageLoaderFactory.getImages().collect {
                _imageFlow.emit(it)
            }
        }
    }

    fun deleteImage(imageId: Int, imageOut: ImageOutData) {
        viewModelScope.launch {
            try {
                val response = imageRepository.deleteImage(imageId)

                if (response.isSuccess) {
                    deleteImageFromDb(imageOut)
                    _deleteImageLiveData.value = response.isSuccess
                } else {
                    _deleteImageLiveData.value = response.isFailure
                }
            } catch (e: Exception) {
                _deleteImageLiveData.value = false
            }
        }
    }

    private fun deleteImageFromDb(imageOut: ImageOutData) {
        viewModelScope.launch(exceptionHandler) {
            imageRepository.deleteImageFromDb(imageOut)

        }

    }
}