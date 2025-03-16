package com.example.board_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.board_api.Navigation.Home
import com.example.board_api.dashboard.ui.DashBoard
import com.example.board_api.ui.theme.Board_APITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Board_APITheme {
//                Login()
                BoredApiApp()
            }
        }
    }
}

@Composable
fun BoredApiApp(modifier:Modifier = Modifier)
{
   val navController= rememberNavController()
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(300)) + slideInVertically(
                    initialOffsetY = { it }, // Slide from bottom
                    animationSpec = tween(300)
                ),
                exit = fadeOut(animationSpec = tween(300)) + slideOutVertically(
                    targetOffsetY = { it }, // Slide out to bottom
                    animationSpec = tween(300)
                )
            ) {
//                MyBottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            NavHost(navController = navController, startDestination = Home.toString()) {
                composable(route = Home.toString()) {
                    DashBoard(navController = navController)
                },
                composable(route = )
            }
        }

    }

}


