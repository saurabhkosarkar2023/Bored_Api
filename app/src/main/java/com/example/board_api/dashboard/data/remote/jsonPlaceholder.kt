package com.example.board_api.dashboard.data.remote

import com.example.board_api.dashboard.model.Posts
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceHolder{
    @GET("posts")
    suspend fun getPosts(): List<Posts>
}