package com.example.moviesapp.data.datasource.remote.movieDbApi.model

import com.google.gson.annotations.SerializedName

data class DatesDTO(
    @SerializedName("maximum") var maximum: String,
    @SerializedName("minimum") var minimum: String
)
