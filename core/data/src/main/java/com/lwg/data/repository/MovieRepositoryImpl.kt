package com.lwg.data.repository

import com.lwg.data.api.MovieApi
import com.lwg.data.mapper.toGenre
import com.lwg.data.mapper.toMovie
import com.lwg.data.mapper.toMovieEntity
import com.lwg.data.util.suspendOnFailureWithErrorHandling
import com.lwg.data.util.suspendOnSuccess
import com.lwg.data_api.MovieRepository
import com.lwg.database.dao.MovieDao
import com.lwg.model.movie.Genre
import com.lwg.model.movie.Movie
import com.lwg.util.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) : MovieRepository{
    override fun getTopRatedMovies(
        page: Int,
        onError: (String) -> Unit
    ): Flow<List<Movie>> = flow {
        val genreList = getMovieGenres(onError).firstOrNull()

        genreList?.let {
            movieApi.getTopRatedMovies(page)
                .suspendOnFailureWithErrorHandling(onError)
                .suspendOnSuccess {
                    emit(
                        response.results.map { movie ->
                            movie.toMovie(genreList)
                        }
                    )
                }
        }
    }

    override fun getMovieGenres(
        onError: (String) -> Unit
    ): Flow<List<Genre>> = flow {
        movieApi.getMovieGenres()
            .suspendOnFailureWithErrorHandling(onError)
            .suspendOnSuccess { emit(response.genres.map { it.toGenre() }) }
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieDao.getMovies().map { movieEntities ->
            movieEntities.map { it.toMovie() }
        }
    }

    override suspend fun upsertMovie(movie: Movie) {
        try {
             movieDao.upsertMovie(
                 movie.toMovieEntity()
             )
        } catch (e: Exception) {
            Logger.e("영화 삽입/수정 실패")
        }
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        try {
            movieDao.deleteMovie(id)
        } catch (e: Exception) {
            Logger.e("영화 삭제 실패")
        }
    }
}