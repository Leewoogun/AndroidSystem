package com.lwg.data_api

import com.lwg.model.movie.MovieDetail
import com.lwg.model.movie.Review
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetail(
        movieId: Int,
        onError: (String) -> Unit
    ) : Flow<MovieDetail>

    fun getMovieReviews(
        movieId: Int,
        page: Int,
        onError: (String) -> Unit
    ) : Flow<Review>
}