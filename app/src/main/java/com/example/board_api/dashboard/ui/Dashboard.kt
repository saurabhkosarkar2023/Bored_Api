package com.example.board_api.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.board_api.CommonAppBar

@Composable
fun DashBoard(
    navController: NavController,
    viewModel: DashBoardViewModel =  hiltViewModel()
) {

LaunchedEffect(Unit) {
    viewModel.fetchPosts()
}
    val posts by viewModel.activities.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    Scaffold(
        topBar = { CommonAppBar("DashBoard") },
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
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
                    items(posts.size) { post ->
                       ListItem(
                            headlineContent = {
                                Text(
                                    text = "${posts[post].id}. ${posts[post].title}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Start
                                )
                            }
                       )

                    }
                    }
                }
            }

    }
}