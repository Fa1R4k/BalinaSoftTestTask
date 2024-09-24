package com.example.balinasofttesttask.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balinasofttesttask.domain.model.ImageInData
import com.example.balinasofttesttask.domain.model.ImageOutData
import com.example.balinasofttesttask.domain.repositories.ImageRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CameraViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val _imageOutDataLivedata = MutableLiveData<ImageOutData>()
    val liveData: LiveData<ImageOutData> get() = _imageOutDataLivedata
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun uploadPhoto(imageIn: ImageInData) {
        viewModelScope.launch(exceptionHandler) {
            val result = repository.uploadImage(imageIn)
            _imageOutDataLivedata.value = result
        }
    }

}