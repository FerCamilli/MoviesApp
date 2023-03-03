package com.example.moviesapp.data.datasource.remote.firebaseStorage

import android.net.Uri
import com.example.moviesapp.domain.util.ApiResponse
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class FirebaseStorageDataSourceImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage,
    private val ioDispatcher: CoroutineDispatcher
) : FirebaseStorageDataSource {

    companion object {
        const val FILENAME_EXTENSION_JPG = ".jpg"
        const val FILE_DIRECTORY_IMAGE = "images/"
    }

    override fun addImage(imageUri: Uri): Flow<ApiResponse<Uri>> {
        val fileName = UUID.randomUUID().toString() + FILENAME_EXTENSION_JPG
        val storageRef = firebaseStorage.reference.child(FILE_DIRECTORY_IMAGE + fileName)

        return callbackFlow {
            storageRef.putFile(imageUri)
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        trySend(ApiResponse.Success(uri))
                    }
                }
                .addOnFailureListener {
                    OnFailureListener {
                        trySend(ApiResponse.Failure(it.toString()))
                    }
                }
            awaitClose { close() }
        }.catch { throwable ->
            emit(ApiResponse.Failure(throwable.toString()))
        }.flowOn(
            ioDispatcher
        )
    }
}
