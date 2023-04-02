package com.example.harrypotter.Domain.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wand(
    val core : String,
    val length: Double,
    val wood: String
) : Parcelable