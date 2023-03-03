package com.example.moviesapp.di.modules

import com.example.moviesapp.data.datasource.remote.movieDbApi.service.MovieApiService
import com.example.moviesapp.di.util.MOVIE_API_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieApiServiceModule {

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(MOVIE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }
}
