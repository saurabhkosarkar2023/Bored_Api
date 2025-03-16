package com.example.board_api.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.board_api.CommonAppBar

@Composable
fun DashBoard(
    navController: NavController,
    viewModel: DashBoardViewModel = viewModel()
) {
    val activity by viewModel.activities.collectAsState()
    Scaffold(
        topBar = { CommonAppBar("DashBoard") },
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp),
            userScrollEnabled = true,
        ) {
            items(activity.size, key = { item -> item }) { activ ->
                Text(
                    text = "${activity[activ].id} ${activity[activ].name}",

                    )
            }
        }
    }
}

//@Composable
//@Preview
//fun DashBoardPreview() {
//    DashBoard()
//}