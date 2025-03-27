package com.example.board_api.auth.data.remote

import com.example.board_api.auth.model.User
import retrofit2.http.GET

interface AuthenticationApi {
    suspend fun login():User
    suspend fun register():User
    suspend fun logOut() : String
}