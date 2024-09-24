package com.example.balinasofttesttask.ui.base_view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balinasofttesttask.data.source.TokenDataSource
import com.example.balinasofttesttask.domain.model.AuthData
import com.example.balinasofttesttask.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TokenViewModel @Inject constructor(
    private val prefs: TokenDataSource
) : ViewModel() {
    private val _userIsAuthorizedLiveData = MutableLiveData<Boolean>()
    val userIsAuthorizedLiveData: LiveData<Boolean> get() = _userIsAuthorizedLiveData

    fun setTokens(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            prefs.setAccessToken(accessToken)
        }
        _userIsAuthorizedLiveData.value = true
    }

    fun isUserAuth() = prefs.getAccessToken().isEmpty()

    fun deleteTokens() {
        viewModelScope.launch(Dispatchers.IO) {
            prefs.deleteToken()
        }
    }
}