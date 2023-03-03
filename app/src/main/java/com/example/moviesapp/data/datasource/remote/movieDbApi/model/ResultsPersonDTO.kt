package com.example.moviesapp.data.datasource.remote.movieDbApi.model

import com.google.gson.annotations.SerializedName

data class ResultsPersonDTO(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var moviePersonList: ArrayList<MoviePersonDTO>,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("total_results") var totalResults: Int
)
