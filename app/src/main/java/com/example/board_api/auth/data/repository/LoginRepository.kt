package com.example.board_api.auth.data.repository

import com.example.board_api.auth.data.di.NetworkModule
import com.example.board_api.auth.data.remote.AuthenticationApi
import com.example.board_api.auth.model.User
import javax.inject.Inject

class LoginRepository @Inject constructor(
    loginApi: AuthenticationApi
) {
    fun signIn(): User = User(userId = "Saurabh", token = "Saurabh", email = "Saurabh")

    fun signUp(): User = User(userId = "Saurabh", token = "Saurabh", email = "Saurabh")

    fun logout(): String = "Saurabh"
}