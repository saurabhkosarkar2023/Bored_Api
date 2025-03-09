package com.example.board_api

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.board_api.auth.data.AuthRepository
import com.example.board_api.auth.ui.AuthViewModel
import com.example.board_api.auth.ui.Login
import com.example.board_api.ui.theme.Board_APITheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {

            val authViewModel = AuthViewModel(AuthRepository())

            Board_APITheme {
                Login(authViewModel)  //authViewModel
//                CustomButton()
//                DashBoard()
            }
        }
    }
}


