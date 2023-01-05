package com.example.androidgithubapi.di

import android.content.Context
import com.example.androidgithubapi.network.ClientService
import com.example.androidgithubapi.network.RetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context
    ): ClientService =  RetrofitAPI.getInstance().create(ClientService::class.java)

}