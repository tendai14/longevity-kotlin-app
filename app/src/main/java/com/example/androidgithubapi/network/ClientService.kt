package com.example.androidgithubapi.network

import com.example.androidgithubapi.model.UserDetail
import com.example.androidgithubapi.model.UserRepository
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {
    @GET("users")
    suspend fun getUsers(): NetworkResponse<List<UserDetail>, Error>
    @GET("users/{login}/repos")
    suspend fun getRepos(@Path("login") login: String): NetworkResponse<List<UserRepository>, Error>
}