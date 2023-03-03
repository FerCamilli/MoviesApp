package com.example.moviesapp.data.datasource.remote.movieDbApi.model

import com.google.gson.annotations.SerializedName

data class KnownForDTO(
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("first_air_date") var firstAirDate: String,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int>,
    @SerializedName("id") var id: Int,
    @SerializedName("media_type") var mediaType: String,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var originCountry: ArrayList<String>,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("original_name") var originalName: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int
)
