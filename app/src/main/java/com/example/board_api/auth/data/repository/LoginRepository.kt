package com.example.board_api.auth.data.repository

import android.util.Log
import com.example.board_api.auth.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class LoginRepository @Inject constructor(
    private var loginApi: FirebaseAuth,
) {
    suspend fun signIn(email: String, password: String): Result<User> {
        try {
            var response = loginApi.signInWithEmailAndPassword(email, password).await()
            if (response.user != null
                ) {
                var rawUser = response.user;
                var token = rawUser?.getIdToken(true)?.await()?.token ?: ""
                val user = User(
                    userId = rawUser?.uid,
                    token = token,
                    email = rawUser?.email ?: "",
                )
                Log.d("Login Repository","Inside the if condition $response")
                return Result.success(user)
            }
            Log.d("Login Repository","Outside the if condition ${response.user}")
            return Result.failure(Exception("Login failed!"))

        } catch (e: Exception) {
            Log.d("Login Repository Error ","Error bhai >> $e")
            return Result.failure(e)
        }
    }

    suspend fun signUp(email: String, password: String): Result<User> {
        try {
            var response = loginApi.createUserWithEmailAndPassword(email, password)
            if (response.isSuccessful) {
                var rawUser = response.result.user;
                var token = rawUser?.getIdToken(true)?.await()?.token ?: ""
                val user = User(
                    userId = rawUser?.uid,
                    token = token,
                    email = rawUser?.email ?: "",
                )
                return Result.success(user)
            }
            return Result.failure(Exception(response.exception))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun logout(): Boolean {
        loginApi.signOut()
        loginApi.currentUser ?: return true
        return true;
    }
}