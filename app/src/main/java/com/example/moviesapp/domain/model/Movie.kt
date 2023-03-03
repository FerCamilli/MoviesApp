package com.example.moviesapp.domain.model

data class Movie(
    val title: String,
    val overview: String,
    val voteScore: Double,
    val posterUrlPath: String
)
