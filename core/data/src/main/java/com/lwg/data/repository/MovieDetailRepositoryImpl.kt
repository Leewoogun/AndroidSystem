package com.lwg.data.repository

import com.lwg.data.api.MovieApi
import com.lwg.data.api.MovieDetailApi
import com.lwg.data.mapper.toGenre
import com.lwg.data.mapper.toMovieDetail
import com.lwg.data.mapper.toReview
import com.lwg.data.util.suspendOnFailureWithErrorHandling
import com.lwg.data.util.suspendOnSuccess
import com.lwg.data_api.MovieDetailRepository
import com.lwg.model.movie.Genre
import com.lwg.model.movie.MovieDetail
import com.lwg.model.movie.Review
import com.lwg.util.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MovieDetailRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDetailApi: MovieDetailApi
): MovieDetailRepository {
    override fun getMovieDetail(movieId: Int, onError: (String) -> Unit): Flow<MovieDetail>  = flow {
        movieDetailApi.getMovieDetail(
            movieId = movieId,
        )
        .suspendOnFailureWithErrorHandling(onError)
        .suspendOnSuccess {
            emit(
                response.toMovieDetail()
            )
        }
    }

    override fun getMovieReviews(
        movieId: Int,
        page: Int,
        onError: (String) -> Unit,
    ): Flow<Review> = flow {
        movieDetailApi.getMovieReviews(
            movieId = movieId,
            page = page
        )
            .suspendOnFailureWithErrorHandling(onError)
        .suspendOnSuccess {
            Logger.i("emit 되니?")
            emit(
                response.toReview()
            )
        }
    }

    override fun getMovieGenres(
        onError: (String) -> Unit
    ): Flow<List<Genre>> = flow {
        movieApi.getMovieGenres()
            .suspendOnFailureWithErrorHandling(onError)
            .suspendOnSuccess { emit(response.genres.map { it.toGenre() }) }
    }
}