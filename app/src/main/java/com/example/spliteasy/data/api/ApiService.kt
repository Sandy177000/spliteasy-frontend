package com.example.spliteasy.data.api

import com.example.spliteasy.data.model.LoginRequest
import com.example.spliteasy.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<User>
}