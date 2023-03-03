package com.example.moviesapp.domain.usecase

import android.net.Uri
import com.example.moviesapp.domain.repository.FirebaseRepository
import com.example.moviesapp.domain.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddImageUseCase @Inject constructor(
    private val repository: FirebaseRepository
) {
    operator fun invoke(imageUri: Uri): Flow<ApiResponse<Uri>> {
        return repository.addImage(imageUri)
    }
}
