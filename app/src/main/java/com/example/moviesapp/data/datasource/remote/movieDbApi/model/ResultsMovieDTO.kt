package com.example.moviesapp.data.datasource.remote.movieDbApi.model

import com.google.gson.annotations.SerializedName

data class ResultsMovieDTO(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var movieList: ArrayList<MovieDTO>,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("total_results") var totalResults: Int
)
