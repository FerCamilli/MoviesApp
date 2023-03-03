package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.repository.MovieApiRepository
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieApiRepository
) {
    operator fun invoke(): Flow<ApiResponse<List<Movie>>> {
        return repository.getTopRatedMovies()
    }
}
