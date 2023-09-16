package com.mahsunsayak.filmtanitim.service

import com.google.gson.annotations.SerializedName
import com.mahsunsayak.filmtanitim.model.Film

data class MovieResponse(
    @SerializedName("results") val results: List<Film>
)
