package com.example.moviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviesapp.domain.model.MoviePerson
import com.example.moviesapp.domain.usecase.GetPopularMoviePeopleUseCase
import com.example.moviesapp.domain.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPopularMoviePeopleUseCase: GetPopularMoviePeopleUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    suspend fun getMostPopularMoviePerson(): Flow<ApiResponse<List<MoviePerson>>> {
        return flow {
            getPopularMoviePeopleUseCase()
                .collect { result ->
                    when (result) {
                        is ApiResponse.Success -> emit(ApiResponse.Success(result.data))
                        is ApiResponse.Failure -> emit(ApiResponse.Failure(result.error))
                    }
                }
        }.flowOn(ioDispatcher)
    }
}
