package com.example.spliteasy.data.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileImage: String? = null,
)