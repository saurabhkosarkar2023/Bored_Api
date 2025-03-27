package com.example.board_api.auth.data.di

import androidx.security.crypto.EncryptedSharedPreferences
import com.example.board_api.auth.data.remote.AuthenticationApi
import com.example.board_api.auth.model.User
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule : AuthenticationApi {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val sharedPreferences =
//        EncryptedSharedPreferences.create(
//            context,
//            "secure_prefs",
//            masterKey,
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )

    override suspend fun login(): User {
        return User(userId = "Saurabh", token = "Saurabh", email = "Saurabh")
    }
    override suspend fun register(): User {
       return User(userId = "Saurabh", token = "Saurabh", email = "Saurabh")
    }
    override suspend fun logOut()="Saurabh"

}