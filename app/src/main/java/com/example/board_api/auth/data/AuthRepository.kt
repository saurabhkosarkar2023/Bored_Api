package com.example.board_api.auth.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun login(email:String,password: String): Result<String> {
        return try {
            val response = auth.signInWithEmailAndPassword(email,password).await()
            Log.d("auth response","Response >> ${response.user}")
            Result.success("Login Successful")  //response.user
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }
    fun isUserLoggedIn() : Boolean {
        return auth.currentUser != null;
    }

    fun logOut() : String {
        auth.signOut()
        return "User Logged Out."
    }
}