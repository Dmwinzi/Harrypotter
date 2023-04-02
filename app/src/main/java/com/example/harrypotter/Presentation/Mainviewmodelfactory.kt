package com.example.harrypotter.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harrypotter.Data.RepositoryImpl

class Mainviewmodelfactory(private val repository: RepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Mainviewmodel::class.java)){
            return Mainviewmodel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Viewmodel not found")
    }


}