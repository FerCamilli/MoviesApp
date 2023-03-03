package com.example.moviesapp.data.repository

import android.net.Uri
import com.example.moviesapp.data.datasource.remote.firebaseStorage.FirebaseStorageDataSource
import com.example.moviesapp.domain.repository.FirebaseRepository
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseStorageDataSource
) : FirebaseRepository {
    override fun addImage(imageUri: Uri): Flow<ApiResponse<Uri>> {
        return dataSource.addImage(imageUri)
    }
}
