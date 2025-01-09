package com.lwg.data.api

import com.lwg.data.model.GenreListResponse
import com.lwg.data.model.TopRatedMovieResponse
import com.lwg.data.util.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MovieApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
    ) : ApiResult<TopRatedMovieResponse>

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") language: String = "en"
    ) : ApiResult<GenreListResponse>
}