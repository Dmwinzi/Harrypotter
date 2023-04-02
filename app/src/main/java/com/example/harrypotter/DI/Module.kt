package com.example.harrypotter.DI

import com.example.harrypotter.Domain.Repository.Repository
import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Provides
    @Singleton
    fun providerepository (retrofit: Retrofit) : Repository{
        return retrofit.create(Repository::class.java)
    }

}