package com.lwg.data_api

import com.lwg.model.movie.Genre
import com.lwg.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTopRatedMovies(
        page: Int,
        onError: (String) -> Unit
    ): Flow<List<Movie>>

    fun getMovieGenres(
        onError: (String) -> Unit
    ): Flow<List<Genre>>

    fun getFavoriteMovie() : Flow<List<Movie>>

    suspend fun upsertMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(id: Int)
}