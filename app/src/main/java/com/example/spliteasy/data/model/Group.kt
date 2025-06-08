package com.example.spliteasy.data.model

data class Group(
    val id: String,
    val name: String,
    val members: List<User>,
    val description: String?,
    val createdAt: String,
    val createdBy: User,
    val totalBalance: Double,
    val currency: String = "INR",
    val imageUrl: String? = null
)