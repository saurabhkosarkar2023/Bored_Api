package com.example.board_api.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class BottomNavItem<T: Any>(
    val route: T,
    val title: String,
    val icon: ImageVector
)

@Serializable
data object Home
@Serializable
data object Saved
@Serializable
data object Login
@Serializable
data object SignUp

val BottomNavItems= listOf(
    BottomNavItem(route = Home, title = "Home", icon = Icons.Filled.Home),
    BottomNavItem(route = Saved, title = "Saved", icon = Icons.Filled.AccountBox)
)

sealed class Screens(val route:String){
    object Home:Screens("Home")
    object Login:Screens("Login")
    object Saved:Screens("Saved")
    object SignUp:Screens("SignUp")
}