package com.example.androidgithubapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    private const val baseUrl = "https://api.github.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            // we need to add converter factory to
            // convert JSON object to Java object
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

}