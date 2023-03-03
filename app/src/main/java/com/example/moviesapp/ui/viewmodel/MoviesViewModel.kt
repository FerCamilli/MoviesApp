package com.example.moviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.usecase.GetPopularMoviesUseCase
import com.example.moviesapp.domain.usecase.GetRecommendedMoviesUseCase
import com.example.moviesapp.domain.usecase.GetTopRatedMoviesUseCase
import com.example.moviesapp.domain.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isPartialApiCallSuccessful = MutableStateFlow(true)
    private val isPartialApiCallSuccessful = _isPartialApiCallSuccessful.asStateFlow()

    private val _isSuccessfulApiCall: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isSuccessfulApiCall = _isSuccessfulApiCall.asStateFlow()

    private val _popularMoviesList: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)
    val popularMoviesList = _popularMoviesList.asStateFlow()

    private val _topRatedMoviesList: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)
    val topRatedMoviesList = _topRatedMoviesList.asStateFlow()

    private val _recommendedMoviesList: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)
    val recommendedMoviesList = _recommendedMoviesList.asStateFlow()

    fun getMoviesLists() {
        viewModelScope.launch {
            launch {
                getPopularMoviesList()
                getTopRatedMoviesList()
                getRecommendedMoviesList()
            }.join()

            if (isPartialApiCallSuccessful.value) {
                setSuccessfulApiCallAsTrue()
                setLoadingAsFalse()
            } else {
                setSuccessfulApiCallAsFalse()
                setLoadingAsFalse()
            }
        }
    }

    private suspend fun getPopularMoviesList() {
        getPopularMoviesUseCase()
            .collect { result ->
                when (result) {
                    is ApiResponse.Success -> {
                        updateListWith(_popularMoviesList, result.data)
                    }
                    is ApiResponse.Failure -> {
                        setLoadingAsFalse()
                        setPartialApiCallSuccessfulAsFalse()
                    }
                }
            }
    }

    private suspend fun getTopRatedMoviesList() {
        getTopRatedMoviesUseCase()
            .collect { result ->
                when (result) {
                    is ApiResponse.Success -> {
                        updateListWith(_topRatedMoviesList, result.data)
                    }
                    is ApiResponse.Failure -> {
                        setLoadingAsFalse()
                        setPartialApiCallSuccessfulAsFalse()
                    }
                }
            }
    }

    private suspend fun getRecommendedMoviesList() {
        getRecommendedMoviesUseCase()
            .collect { result ->
                when (result) {
                    is ApiResponse.Success -> {
                        updateListWith(_recommendedMoviesList, result.data)
                    }
                    is ApiResponse.Failure -> {
                        setLoadingAsFalse()
                        setPartialApiCallSuccessfulAsFalse()
                    }
                }
            }
    }

    private fun updateListWith(
        mutableList: MutableStateFlow<List<Movie>?>,
        obtainedList: List<Movie>
    ) {
        mutableList.update { obtainedList }
    }

    fun setLoadingAsTrue() {
        _isLoading.update { true }
    }

    private fun setLoadingAsFalse() {
        _isLoading.update { false }
    }

    private fun setPartialApiCallSuccessfulAsFalse() {
        _isPartialApiCallSuccessful.update { false }
    }

    private fun setSuccessfulApiCallAsTrue() {
        _isSuccessfulApiCall.update { true }
    }

    private fun setSuccessfulApiCallAsFalse() {
        _isSuccessfulApiCall.update { false }
    }
}
