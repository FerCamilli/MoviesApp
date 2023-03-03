package com.example.moviesapp.di.modules

import com.example.moviesapp.data.datasource.remote.firebaseStorage.FirebaseStorageDataSource
import com.example.moviesapp.data.datasource.remote.firebaseStorage.FirebaseStorageDataSourceImpl
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideFirebaseStorageDataSource(
        firebaseStorage: FirebaseStorage,
        ioDispatcher: CoroutineDispatcher
    ): FirebaseStorageDataSource {
        return FirebaseStorageDataSourceImpl(firebaseStorage, ioDispatcher)
    }
}
