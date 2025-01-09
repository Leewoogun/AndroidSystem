package com.lwg.data.repository

import com.lwg.data.api.MovieApi
import com.lwg.data.mapper.toMovie
import com.lwg.data.util.suspendOnFailureWithErrorHandling
import com.lwg.data.util.suspendOnSuccess
import com.lwg.data_api.MovieRepository
import com.lwg.model.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository{
    override fun getTopRatedMovies(
        page: Int,
        onError: (String) -> Unit
    ): Flow<List<Movie>> = flow {
        movieApi.getTopRatedMovies(page)
            .suspendOnFailureWithErrorHandling(onError)
            .suspendOnSuccess {
                emit(
                    response.results.map { it.toMovie() }
                )
            }
    }

    override fun getMovieGenres(
        onError: (String) -> Unit
    ): Flow<List<String>> = flow {
        movieApi.getMovieGenres()
            .suspendOnFailureWithErrorHandling(onError)
            .suspendOnSuccess { response.genres.map { it.name } }
    }
}