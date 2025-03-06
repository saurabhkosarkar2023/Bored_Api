package com.example.board_api.auth.model

import com.google.gson.annotations.SerializedName


data class User (
@SerializedName("email") val email:String
)