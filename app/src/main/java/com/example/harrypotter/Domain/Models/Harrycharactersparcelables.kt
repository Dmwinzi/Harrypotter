package com.example.harrypotter.Domain.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Harrycharactersparcelables(
    val actor: String = "",
    val alive: Boolean = false,
    val alternate_actors: List<String> = emptyList(),
    val alternate_names: List<String> = emptyList(),
    val ancestry: String = "",
    val dateOfBirth: String? = null,
    val eyeColour: String = "",
    val gender: String = "",
    val hairColour: String = "",
    val hogwartsStaff: Boolean = false,
    val hogwartsStudent: Boolean = false,
    val house: String = "",
    val id: String = "",
    val image: String = "",
    val name: String = "",
    val patronus: String = "",
    val species: String = "",
    val wand: Wand = Wand("","".toDouble(),""),
    val wizard: Boolean = false,
    val yearOfBirth: Int = 0
): Parcelable