package com.example.board_api.Navigation

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class TopLevelRoutes<T: Any>(
    val route: T,
    val title: String,
    val icon: ImageVector
)

@Serializable
data object Home

@Serializable
data object Saved

val Top_Level_Routes= listOf(
    TopLevelRoutes(route = Home, title = "Home", icon = Icons.Filled.Home),
    TopLevelRoutes(route = Saved, title = "Saved", icon = Icons.Filled.AccountBox)
)