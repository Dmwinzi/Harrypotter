package com.example.harrypotter.Domain.Models

import retrofit2.Response

sealed class Resource{

    data class Success(val data : List<HarrycharsItem>) : Resource()
    data class Error(val message : String) : Resource()
    object Loading : Resource()
}
