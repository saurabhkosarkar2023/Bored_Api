package com.example.board_api.auth.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
//    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    suspend fun login(email:String,password: String): Result<String> {
        Log.d("AuthRepository","Came for network call email >> $email and pass >> $password")
        try {
            val response = auth.signInWithEmailAndPassword(email,password).await()
            val user=response.user
            Log.d("auth response","Response kya ara haii bhai  >> $user")
            return Result.success(user?.email.toString())  //response.user
        }
        catch (e: Exception){
            return Result.failure(e)
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