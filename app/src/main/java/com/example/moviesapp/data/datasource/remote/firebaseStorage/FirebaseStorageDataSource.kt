package com.example.moviesapp.data.datasource.remote.firebaseStorage

import android.net.Uri
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface FirebaseStorageDataSource {
    fun addImage(imageUri: Uri): Flow<ApiResponse<Uri>>
}
