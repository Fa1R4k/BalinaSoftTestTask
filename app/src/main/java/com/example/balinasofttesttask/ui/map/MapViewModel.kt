package com.example.balinasofttesttask.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.balinasofttesttask.domain.model.ImageOutData
import com.example.balinasofttesttask.domain.repositories.ImageRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {

    private val _imageListLiveData: MutableLiveData<List<ImageOutData>> = MutableLiveData()
    val imageListLiveData: LiveData<List<ImageOutData>> get() = _imageListLiveData


    fun getImageList() {
        viewModelScope.launch {
            _imageListLiveData.value = imageRepository.getImageFromDb()
        }
    }
}