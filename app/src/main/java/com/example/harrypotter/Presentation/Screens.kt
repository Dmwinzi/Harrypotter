package com.example.harrypotter.Presentation

sealed class Screens(var route : String){

    object Characters : Screens("Characters")

    object Characterinfo : Screens("Characterinfo")

}
