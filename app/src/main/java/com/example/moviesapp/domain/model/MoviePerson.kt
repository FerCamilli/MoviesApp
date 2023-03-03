package com.example.moviesapp.domain.model

data class MoviePerson(
    val name: String,
    val role: String,
    val popularity: Double,
    val pictureUrlPath: String,
    val bestKnownFor: String?,
    val bestKnownForOverview: String
)
