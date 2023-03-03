package com.example.moviesapp.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.usecase.AddImageUseCase
import com.example.moviesapp.domain.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val addImageUseCase: AddImageUseCase
) : ViewModel() {

    companion object {
        const val API_CALL_BLANK_MESSAGE = ""
    }

    private val _isSuccessfulApiCall: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isSuccessfulApiCall = _isSuccessfulApiCall.asStateFlow()

    private val _apiCallResultMessage = MutableStateFlow(API_CALL_BLANK_MESSAGE)
    val apiCallResultMessage = _apiCallResultMessage.asStateFlow()

    fun uploadImage(imageUri: Uri) {
        viewModelScope.launch {
            addImageUseCase(imageUri)
                .collect { result ->
                    when (result) {
                        is ApiResponse.Success -> {
                            updateApiCallResultMessage(result.data.toString())
                            setSuccessfulApiCallAsTrue()
                        }
                        is ApiResponse.Failure -> {
                            updateApiCallResultMessage(result.error)
                            setSuccessfulApiCallAsFalse()
                        }
                    }
                }
        }
    }

    private fun updateApiCallResultMessage(apiResponseMessage: String) {
        _apiCallResultMessage.update { apiResponseMessage }
    }

    private fun setSuccessfulApiCallAsTrue() {
        _isSuccessfulApiCall.update { true }
    }

    private fun setSuccessfulApiCallAsFalse() {
        _isSuccessfulApiCall.update { false }
    }

    fun resetIsApiSuccessfulCall() {
        _isSuccessfulApiCall.update { null }
    }
}
