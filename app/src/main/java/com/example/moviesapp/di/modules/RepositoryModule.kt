package com.example.moviesapp.di.modules

import com.example.moviesapp.data.datasource.remote.firebaseStorage.FirebaseStorageDataSource
import com.example.moviesapp.data.datasource.remote.movieDbApi.service.MovieApiService
import com.example.moviesapp.data.repository.FirebaseRepositoryImpl
import com.example.moviesapp.data.repository.MovieApiRepositoryImpl
import com.example.moviesapp.domain.repository.FirebaseRepository
import com.example.moviesapp.domain.repository.MovieApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieApiRepository(
        apiService: MovieApiService,
        ioDispatcher: CoroutineDispatcher
    ): MovieApiRepository {
        return MovieApiRepositoryImpl(apiService, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        dataSource: FirebaseStorageDataSource
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(dataSource)
    }
}
