package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviePerson
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface MovieApiRepository {
    fun getPopularMoviePeople(): Flow<ApiResponse<List<MoviePerson>>>
    fun getPopularMovies(): Flow<ApiResponse<List<Movie>>>
    fun getTopRatedMovies(): Flow<ApiResponse<List<Movie>>>
    fun getRecommendedMovies(): Flow<ApiResponse<List<Movie>>>
}
