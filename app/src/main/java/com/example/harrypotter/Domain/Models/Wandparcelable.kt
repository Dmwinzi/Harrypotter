package com.example.harrypotter.Domain.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wandparcelable(
    val core : String = "",
    val length: Double = "".toDouble(),
    val wood: String = ""
) : Parcelable