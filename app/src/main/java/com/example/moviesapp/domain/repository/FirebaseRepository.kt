package com.example.moviesapp.domain.repository

import android.net.Uri
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun addImage(imageUri: Uri): Flow<ApiResponse<Uri>>
}
