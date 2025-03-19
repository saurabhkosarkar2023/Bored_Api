package com.example.board_api.Saved.UI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.board_api.CommonAppBar

@Composable
fun SavedActivity(modifier: Modifier=Modifier,navController: NavController){
    Scaffold(
        topBar = { CommonAppBar("Saved Activity") }
    ) {innerPadding->
        Column( modifier = Modifier.fillMaxSize().padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "Saved Activity Screen")
        }

    }
}