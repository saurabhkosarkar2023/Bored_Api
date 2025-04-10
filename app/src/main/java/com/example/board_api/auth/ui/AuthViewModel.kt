package com.example.board_api.auth.ui

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.board_api.auth.data.repository.LoginRepository
import com.example.board_api.auth.model.User
import com.example.board_api.dashboard.data.SecurePrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginRespository: LoginRepository,
    private val application: Application
) : ViewModel() {
    private val _loginState: MutableStateFlow<Result<User>?> = MutableStateFlow(null)
    var loginState: StateFlow<Result<User>?> = _loginState

    private val _loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var loadingState: StateFlow<Boolean> = _loadingState;

    private val _isTokenAvailable = MutableStateFlow<Boolean?>(null)
    val isTokenAvailable: StateFlow<Boolean?> = _isTokenAvailable

    var isCheckingToken = mutableStateOf(true)

    init {
        checkToken()
    }

    fun checkToken(){
        viewModelScope.launch {
            val token = SecurePrefs.getToken(application.applicationContext)
            _isTokenAvailable.value =!token.isNullOrEmpty()
            isCheckingToken.value=false
        }
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            Log.d("Auth-ViewModel", "Inside viewModel with email $email and pass >> $password")
            _loadingState.value = true
            val response = loginRespository.signIn(email, password)
            response.fold(
                onSuccess = {
                    val token = it.token
                    SecurePrefs.saveToken(application.applicationContext, token)
                    _loginState.value = Result.success(
                        User(
                            email = it.email,
                            token = token,
                            userId = it.userId
                        )
                    )
                },
                onFailure = {
                    _loginState.value=Result.failure(it)
                }
            )
            _loadingState.value = false
            Log.d("Auth-ViewModel response", "Incoming response login >> ${_loginState.value}")
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            Log.d("Auth-ViewModel","Inside signUp() of auth viewmodel with email >> $email and pass >> $password")
            _loadingState.value = true
            val response = loginRespository.signUp(email, password)
            response.fold(
                onSuccess = {
                    val token = it.token
                    SecurePrefs.saveToken(application.applicationContext,token)
                    _loginState.value = Result.success(
                        User(
                            email = it.email,
                            token = token,
                            userId = it.userId,
                        )
                    )
                },
                onFailure = {
                    _loginState.value = Result.failure(it)
                }
            )
            _loadingState.value = false
            Log.d("Auth-ViewModel response", "Incoming response >> ${_loadingState.value}")
        }
    }

    suspend fun logOut() : Boolean {
        viewModelScope.launch {
            Log.d("Auth-ViewModel", "Inside Logout() function.")
            _loadingState.value = true
            loginRespository.logout()
            SecurePrefs.deleteToken(application.applicationContext)
            _loadingState.value = false
        }
        return true
    }
}

