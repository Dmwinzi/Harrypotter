package com.example.harrypotter.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotter.Data.RepositoryImpl
import com.example.harrypotter.Domain.Models.HarrycharsItem
import com.example.harrypotter.Domain.Models.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class Mainviewmodel @Inject constructor(val  repository : RepositoryImpl) : ViewModel() {

    private var characters = MutableStateFlow<Resource>(Resource.Loading)
    var _characters  = characters.asStateFlow()


    init {
        getallcharacters()
    }

     fun getallcharacters () {
        viewModelScope.launch {
            delay(2000)
            try {
                var characterlist  = repository.getcharacters()
                if (characterlist.isSuccessful){
                    characters.value = characterlist.body()?.let { Resource.Success(it) }!!
                }
            }catch (e:Exception){
                com.example.harrypotter.Domain.Models.Resource.Error(e.message.toString())
            }

        }
    }


}