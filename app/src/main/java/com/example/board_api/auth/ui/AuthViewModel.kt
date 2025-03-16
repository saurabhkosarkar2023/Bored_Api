package com.example.board_api.auth.ui

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.MasterKey
import com.example.board_api.auth.data.AuthRepository
import com.example.board_api.auth.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private  val authRepository: AuthRepository) : ViewModel() {
    private val _loginState: MutableStateFlow<Result<User>?> = MutableStateFlow(null)
    val loginState: StateFlow<Result<User>?> = _loginState



    fun login(email: String,password:String){
        viewModelScope.launch {
            Log.d("ViewModel","Inside viewModel with email $email and pass >> $password")
            _loginState.value = authRepository.login(email,password)
            Log.d("ViewModel response","Incoming reponse >> ${_loginState.value}")
        }
    }

    fun logout(){
        authRepository.logOut()
    }

}