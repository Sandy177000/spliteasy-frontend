package com.example.spliteasy.data.repository

import com.example.spliteasy.data.api.RetrofitInstance
import com.example.spliteasy.data.model.LoginRequest
import com.example.spliteasy.data.model.User
import retrofit2.Response

class AuthRepository() {
    private val api = RetrofitInstance.api
    suspend fun login(idToken: String?): Response<User> {
        return api.login(LoginRequest(idToken))
    }
}