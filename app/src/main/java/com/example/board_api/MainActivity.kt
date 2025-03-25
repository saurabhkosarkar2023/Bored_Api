@file:Suppress("UNREACHABLE_CODE")

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.board_api.Navigation.Home
import com.example.board_api.Navigation.Saved
import com.example.board_api.Navigation.BottomNavItems
import com.example.board_api.Navigation.Login
import com.example.board_api.Saved.UI.SavedActivity
import com.example.board_api.auth.ui.Login
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
fun BoredApiApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
                AnimatedVisibility(
                    visible = currentRoute == Home.toString() || currentRoute == Saved.toString(),
                    enter = fadeIn(animationSpec = tween(300)) + slideInVertically(
                        initialOffsetY = { it }, // Slide from bottom
                        animationSpec = tween(300)
                    ),
                    exit = fadeOut(animationSpec = tween(300)) + slideOutVertically(
                        targetOffsetY = { it }, // Slide out to bottom
                        animationSpec = tween(300)
                    )
                ) {
                    BottomNavigationBar(navController = navController)
                }
        },


        content = { padding ->
            NavHostContainer(navController = navController, padding = padding)

        }
    )

}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Login.toString(),
        builder = {
            composable(Home.toString()) {
                DashBoard(navController = navController)
            }
            composable(Saved.toString()) {
                SavedActivity(navController = navController)
            }
            composable(Login.toString()) {
                Login(navController = navController)
            }
        }

    )


}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val selectedIndex = rememberSaveable { mutableStateOf(0) }
    NavigationBar {
        BottomNavItems.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    //navigation
                    navController.navigate(destination.route.toString()) {
                        popUpTo(route = Home.toString())
                        launchSingleTop = true
                    }
//                    navController.navigate(BottomNavItems[index].route){
//                        popUpTo(Home.toString())
//                        launchSingleTop=true
//                    }
                },
                icon = {
                    Icon(imageVector = destination.icon, contentDescription = Home.toString())
                },
                label = {
                    Text(destination.title)
                },
            )
        }
    }
}


