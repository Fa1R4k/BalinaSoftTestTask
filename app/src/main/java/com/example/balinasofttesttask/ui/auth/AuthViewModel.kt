package com.example.balinasofttesttask.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.balinasofttesttask.domain.model.AuthData
import com.example.balinasofttesttask.domain.model.UserData
import com.example.balinasofttesttask.domain.repositories.UserRepository
import com.example.balinasofttesttask.ui.base_view_model.BaseRequest
import com.example.balinasofttesttask.utils.ApiResponse
import com.example.balinasofttesttask.ui.base_view_model.CoroutinesErrorHandler
import java.util.Locale
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val repository: UserRepository,
    private val baseRequest: BaseRequest,
) : ViewModel() {

    private val _userVerifiedLiveData = MutableLiveData<ApiResponse<AuthData>>()
    val userVerifiedLiveData: LiveData<ApiResponse<AuthData>> get() = _userVerifiedLiveData

    private val _coroutinesErrorHandlerLiveData = MutableLiveData<String>()
    val coroutinesErrorHandlerLiveData: LiveData<String> get() = _coroutinesErrorHandlerLiveData

    private val coroutinesErrorHandler = object : CoroutinesErrorHandler {
        override fun onError(message: String) {
            _coroutinesErrorHandlerLiveData.value = message
        }
    }

    fun signUp(login: String, password: String) = baseRequest(
        _userVerifiedLiveData,
        coroutinesErrorHandler,
        viewModelScope
    ) {
        repository.registrationUser(UserData(login, password))
    }

    fun signIn(login: String, password: String) = baseRequest(
        _userVerifiedLiveData,
        coroutinesErrorHandler,
        viewModelScope
    ) {
        repository.loginUser(UserData(login, password))
    }

    fun checkingEnteredLoginData(login: String): Boolean {
        return validateLoginInput(login)
    }

    private fun validateLoginInput(inputLogin: String): Boolean {
        return inputLogin.length in 4..32 && inputLogin.matches(Regex("^[a-z0-9_.\\-@]+$"))
    }

    fun checkingPasswordData(password: String): Boolean {
        return password.length in 8..32
    }
}