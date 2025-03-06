package com.example.board_api.dashboard.model

import com.google.gson.annotations.SerializedName


data class Todo(

  @SerializedName("title") val name:String,
  @SerializedName("id") val id:Int,
  @SerializedName("userId") val userId:String,
  @SerializedName("completed") val status: Boolean,
)
