package com.example.moviesapp.domain.usecase

import com.example.moviesapp.domain.model.MoviePerson
import com.example.moviesapp.domain.repository.MovieApiRepository
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviePeopleUseCase @Inject constructor(
    private val repository: MovieApiRepository
) {
    operator fun invoke(): Flow<ApiResponse<List<MoviePerson>>> {
        return repository.getPopularMoviePeople()
    }
}
