package com.example.ktordemo.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("body")
    val body: String,
    @SerialName("email")
    val email: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("postId")
    val postId: Int
)