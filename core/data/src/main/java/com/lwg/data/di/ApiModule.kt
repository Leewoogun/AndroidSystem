package com.lwg.data.di

import com.lwg.data.api.MovieApi
import com.lwg.data.api.MovieDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun providesMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailApi(retrofit: Retrofit) : MovieDetailApi {
        return retrofit.create(MovieDetailApi::class.java)
    }
}