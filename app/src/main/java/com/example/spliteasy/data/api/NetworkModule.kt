package com.example.spliteasy.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// @Module tells hilt to inject dependencies for third party modules
// here it is used for retrofit
@Module

// @InstallIn(SingletonComponent::class) → Tells Hilt where to install this module
// (here, it's the Application-level scope).
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://192.168.121.84:4000/"

    // @Provides tells Hilt: “Here’s how to create an instance of this class.”
    @Provides
    // @Singleton What it does: Tells Hilt that the provided instance should
    // a single instance is shared across the app.
    @Singleton
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}