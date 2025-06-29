package com.example.spliteasy.data.repository

import com.example.spliteasy.data.api.ApiService
import com.example.spliteasy.data.model.LoginRequestBody
import com.example.spliteasy.data.model.LoginResponseBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val api: ApiService) : AuthRepository {
    override suspend fun login(email: String, password: String): LoginResponseBody? {
        val response = api.login(LoginRequestBody(email, password))
        return response.body()
    }

}