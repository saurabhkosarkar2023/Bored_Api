package com.example.board_api.dashboard.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable



@Serializable
data class Posts(
  val id: Int,
  val userId: Int,
  val title: String,
  val body: String
)
//data class Posts(
//  @SerializedName("id") val id: Int,
//  @SerializedName("userId") val userId: Int,
//  @SerializedName("title") val name: String,
//)