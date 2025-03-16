package com.example.board_api.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val email:String,
    val token:String,
    val userId: String? = null,
)