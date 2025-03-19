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

val BottomNavItems= listOf(
    BottomNavItem(route = Home, title = "Home", icon = Icons.Filled.Home),
    BottomNavItem(route = Saved, title = "Saved", icon = Icons.Filled.AccountBox)
)