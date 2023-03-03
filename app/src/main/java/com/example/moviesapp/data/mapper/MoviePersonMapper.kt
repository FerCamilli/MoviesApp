package com.example.moviesapp.data.mapper // ktlint-disable filename

import com.example.moviesapp.data.datasource.remote.movieDbApi.model.MovieDTO
import com.example.moviesapp.data.datasource.remote.movieDbApi.model.MoviePersonDTO
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviePerson

fun MoviePersonDTO.toModel(): MoviePerson {
    return MoviePerson(
        name,
        knownForDepartment,
        popularity,
        profilePath,
        knownFor.first().name,
        knownFor.first().overview
    )
}

fun MovieDTO.toModel(): Movie {
    return Movie(
        title,
        overview,
        voteAverage,
        posterPath
    )
}
