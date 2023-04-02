package com.example.harrypotter.Data

import com.example.harrypotter.Domain.Models.HarrycharsItem
import com.example.harrypotter.Domain.Repository.Repository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(var repository: Repository) {

    suspend fun getcharacters(): Response<List<HarrycharsItem>> {
        return repository.getcharacters()
    }


}