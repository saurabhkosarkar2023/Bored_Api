package com.example.board_api.dashboard.data.repository

import com.example.board_api.dashboard.data.remote.JsonPlaceHolder
import com.example.board_api.dashboard.model.Posts
import javax.inject.Inject

class PostRepository @Inject constructor(
    private var networkApi: JsonPlaceHolder
){
    suspend fun getPosts(): List<Posts> = networkApi.getPosts()
}