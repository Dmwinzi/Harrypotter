package com.example.harrypotter.Domain.Repository

import com.example.harrypotter.Domain.Models.HarrycharsItem
import retrofit2.Response
import retrofit2.http.GET

interface Repository {

    @GET("characters")
   suspend fun getcharacters() : Response<List<HarrycharsItem>>



}