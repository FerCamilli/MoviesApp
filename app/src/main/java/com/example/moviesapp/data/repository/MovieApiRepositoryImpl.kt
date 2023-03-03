package com.example.moviesapp.data.repository

import com.example.moviesapp.data.datasource.remote.movieDbApi.service.MovieApiService
import com.example.moviesapp.data.mapper.toModel
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MoviePerson
import com.example.moviesapp.domain.repository.MovieApiRepository
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieApiRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val ioDispatcher: CoroutineDispatcher
) : MovieApiRepository {
    override fun getPopularMoviePeople(): Flow<ApiResponse<List<MoviePerson>>> {
        return flow {
            val response = apiService.getPopularMoviePeople().execute()
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    emit(ApiResponse.Success(responseBody.moviePersonList.map { it.toModel() }))
                }
            } else {
                emit(ApiResponse.Failure(response.errorBody().toString()))
            }
        }.catch { throwable ->
            emit(ApiResponse.Failure(throwable.toString()))
        }.flowOn(ioDispatcher)
    }

    override fun getPopularMovies(): Flow<ApiResponse<List<Movie>>> {
        return flow {
            val response = apiService.getPopularMovies().execute()
            if (response.isSuccessful) {
                println("Popular movies ${response.body()?.movieList}")
                response.body()?.let { responseBody ->
                    emit(ApiResponse.Success(responseBody.movieList.map { it.toModel() }))
                }
            } else {
                emit(ApiResponse.Failure(response.errorBody().toString()))
            }
        }.catch { throwable ->
            emit(ApiResponse.Failure(throwable.toString()))
        }.flowOn(ioDispatcher)
    }

    override fun getTopRatedMovies(): Flow<ApiResponse<List<Movie>>> {
        return flow {
            val response = apiService.getTopRatedMovies().execute()
            if (response.isSuccessful) {
                println("Top Rated movies ${response.body()?.movieList}")
                response.body()?.let { responseBody ->
                    emit(ApiResponse.Success(responseBody.movieList.map { it.toModel() }))
                }
            } else {
                emit(ApiResponse.Failure(response.errorBody().toString()))
            }
        }.catch { throwable ->
            emit(ApiResponse.Failure(throwable.toString()))
        }.flowOn(ioDispatcher)
    }

    override fun getRecommendedMovies(): Flow<ApiResponse<List<Movie>>> {
        return flow {
            val response = apiService.getRecommendedMovies().execute()
            if (response.isSuccessful) {
                println("Recommended movies ${response.body()?.movieList}")
                response.body()?.let { responseBody ->
                    emit(ApiResponse.Success(responseBody.movieList.map { it.toModel() }))
                }
            } else {
                emit(ApiResponse.Failure(response.errorBody().toString()))
            }
        }.catch { throwable ->
            emit(ApiResponse.Failure(throwable.toString()))
        }.flowOn(ioDispatcher)
    }
}
