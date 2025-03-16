package com.example.board_api.auth.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.board_api.auth.model.User
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository(context: Context) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val masterKey =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    private val sharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    suspend fun login(email: String, password: String): Result<User> {
        try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            print("What is the response?  >> $response")
            val user = response.user
            if (user != null) {
                val token = user.getIdToken(true).await().token ?: ""
                sharedPreferences.edit().putString("auth_token",token).apply()
                val userModel =
                    User(email = user.email ?: "", token = token ?: "", userId = user.uid)
                Log.d(
                    "auth response",
                    "Response email Model  >> $userModel }"
                )
                return Result.success(userModel)  //response.user
            } else {
                return Result.failure(Exception("User authentication failed!"))
            }


        } catch (e: Exception) {
            return Result.failure(e)
        }
    }


    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null;
    }
    fun getToken():String? {
        return sharedPreferences.getString("auth_token",null)
    }

    fun logOut(): String {
        auth.signOut()
        sharedPreferences.edit().remove("auth_token").apply()
        return "User Logged Out."
    }
}