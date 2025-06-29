package com.example.spliteasy.data.repository

import com.example.spliteasy.data.model.LoginResponseBody

interface AuthRepository {
    suspend fun login(email: String, password: String) :  LoginResponseBody?
}