package com.example.moviesapp.data.datasource.remote.movieDbApi.model

import com.google.gson.annotations.SerializedName

data class MoviePersonDTO(
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("gender") var gender: Int,
    @SerializedName("id") var id: Int,
    @SerializedName("known_for") var knownFor: ArrayList<KnownForDTO>,
    @SerializedName("known_for_department") var knownForDepartment: String,
    @SerializedName("name") var name: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("profile_path") var profilePath: String
)
