package com.example.moviesapp.data.datasource.remote.movieDbApi.service

import com.example.moviesapp.data.datasource.remote.movieDbApi.MOVIE_API_GENERAL_QUERY_LANGUAGE
import com.example.moviesapp.data.datasource.remote.movieDbApi.MOVIE_API_GENERAL_QUERY_PAGE
import com.example.moviesapp.data.datasource.remote.movieDbApi.MOVIE_DB_API_KEY
import com.example.moviesapp.data.datasource.remote.movieDbApi.model.ResultsMovieDTO
import com.example.moviesapp.data.datasource.remote.movieDbApi.model.ResultsPersonDTO
import com.example.moviesapp.data.datasource.remote.movieDbApi.model.ResultsRecommendedMovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("person/popular")
    fun getPopularMoviePeople(
        @Query("api_key") apiKey: String = MOVIE_DB_API_KEY,
        @Query("language") language: String = MOVIE_API_GENERAL_QUERY_LANGUAGE,
        @Query("page") page: String = MOVIE_API_GENERAL_QUERY_PAGE
    ): Call<ResultsPersonDTO>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = MOVIE_DB_API_KEY,
        @Query("language") language: String = MOVIE_API_GENERAL_QUERY_LANGUAGE,
        @Query("page") page: String = MOVIE_API_GENERAL_QUERY_PAGE
    ): Call<ResultsMovieDTO>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = MOVIE_DB_API_KEY,
        @Query("language") language: String = MOVIE_API_GENERAL_QUERY_LANGUAGE,
        @Query("page") page: String = MOVIE_API_GENERAL_QUERY_PAGE
    ): Call<ResultsMovieDTO>

    @GET("movie/now_playing")
    fun getRecommendedMovies(
        @Query("api_key") apiKey: String = MOVIE_DB_API_KEY,
        @Query("language") language: String = MOVIE_API_GENERAL_QUERY_LANGUAGE,
        @Query("page") page: String = MOVIE_API_GENERAL_QUERY_PAGE
    ): Call<ResultsRecommendedMovieDTO>
}
