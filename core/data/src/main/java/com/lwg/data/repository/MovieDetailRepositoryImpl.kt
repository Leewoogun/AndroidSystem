package com.lwg.data.repository

import com.lwg.data.api.MovieDetailApi
import com.lwg.data.mapper.toMovieDetail
import com.lwg.data.mapper.toReview
import com.lwg.data.util.suspendOnFailureWithErrorHandling
import com.lwg.data.util.suspendOnSuccess
import com.lwg.data_api.MovieDetailRepository
import com.lwg.model.movie.MovieDetail
import com.lwg.model.movie.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MovieDetailRepositoryImpl @Inject constructor(
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
            emit(
                response.toReview()
            )
        }
    }
}