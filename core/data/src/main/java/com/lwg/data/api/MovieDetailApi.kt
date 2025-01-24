package com.lwg.data.api

import com.lwg.data.model.MovieDetailResponse
import com.lwg.data.model.MovieReviewsResponse
import com.lwg.data.util.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieDetailApi {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ) : ApiResult<MovieDetailResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ) : ApiResult<MovieReviewsResponse>
}