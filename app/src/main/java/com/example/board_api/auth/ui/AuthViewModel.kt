package com.example.board_api.auth.ui

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.board_api.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private  val authRepository: AuthRepository) : ViewModel() {
    private val _loginState: MutableStateFlow<Result<String>?> = MutableStateFlow(null)
    val loginState: StateFlow<Result<String>?> = _loginState

    fun login(email: String,password:String){
        viewModelScope.launch {
            _loginState.value = authRepository.login(email,password)
        }
    }

    fun logout(){
        authRepository.logOut()
    }

}