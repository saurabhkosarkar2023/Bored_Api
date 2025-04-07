package com.example.board_api.dashboard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.board_api.CommonAppBar
import com.example.board_api.CustomLazyColumn
import com.example.board_api.Navigation.Screens
import com.example.board_api.auth.ui.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun DashBoard(
    navController: NavController,
    viewModel: DashBoardViewModel = hiltViewModel()
) {
    val coroutingScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }
    val posts by viewModel.activities.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val authViewModel: AuthViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            CommonAppBar(
                title = "DashBoard",
                actionIcon = {
                    IconButton(
                        onClick = {
                            coroutingScope.launch {
                                val response = authViewModel.logOut()
                                if (response) {
                                    navController.navigate(Screens.Login.route) {
                                        popUpTo(Screens.Home.route) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            }
                        }

                    ) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout")
                    }
                },
            )
        },
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 70.dp)
        ) {
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                CustomLazyColumn(posts)
            }
        }

    }
}