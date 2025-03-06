package com.example.board_api.dashboard.data

import com.example.board_api.dashboard.model.Todo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ApiService{
    @GET("todos/")
    suspend fun getTodo(): List<Todo>
}

object RetrofitClient{
    private const val BASE_URL="https://jsonplaceholder.typicode.com/"
    val apiService: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
    }
}