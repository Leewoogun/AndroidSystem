package com.lwg.database.di

import com.lwg.database.LwgDatabase
import com.lwg.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    @Singleton
    fun providesMovieDao(
        database: LwgDatabase
    ) : MovieDao = database.movieDao()
}