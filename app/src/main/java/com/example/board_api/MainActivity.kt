package com.example.board_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.board_api.auth.data.AuthRepository
import com.example.board_api.auth.ui.AuthViewModel
import com.example.board_api.auth.ui.Login
import com.example.board_api.ui.theme.Board_APITheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.initialize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {

            val authViewModel = AuthViewModel(AuthRepository())
            Board_APITheme {
                Login(authViewModel)
//                CustomButton()
//                DashBoard()
            }
        }
    }
}


