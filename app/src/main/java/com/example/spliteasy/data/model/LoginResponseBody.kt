package com.example.spliteasy.data.model

data class LoginResponseBody(
    val name: String,
    val email: String,
    val profileImage: String? = null,
    val accessToken: String,
    val error : String
)
