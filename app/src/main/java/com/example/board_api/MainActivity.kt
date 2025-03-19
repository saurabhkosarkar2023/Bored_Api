@file:Suppress("UNREACHABLE_CODE")

package com.example.board_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
    Scaffold(
//        bottomBar = {
//            AnimatedVisibility(
//                visible = true,
//                enter = fadeIn(animationSpec = tween(300)) + slideInVertically(
//                    initialOffsetY = { it }, // Slide from bottom
//                    animationSpec = tween(300)
//                ),
//                exit = fadeOut(animationSpec = tween(300)) + slideOutVertically(
//                    targetOffsetY = { it }, // Slide out to bottom
//                    animationSpec = tween(300)
//                )
//            ) {
////                MyBottomNavigation(navController = navController)
//            }
//        }
        bottomBar = {
            BottomNavigationBar(navController = navController)
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
    NavHost(navController = navController,
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
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route.toString())
                },
                icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.title) },
                label = { Text(text = navItem.title) },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.surface,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        }
    }
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


