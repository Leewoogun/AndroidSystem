package com.lwg.data.di

import com.lwg.data.repository.MovieRepositoryImpl
import com.lwg.data_api.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsMovieRepository(movieRepository: MovieRepositoryImpl) : MovieRepository
}