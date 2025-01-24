package com.lwg.data.mapper

import com.lwg.data.model.MovieDetailResponse
import com.lwg.model.movie.MovieDetail

internal fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    movieTitle = original_title,
    overview = overview,
    posterPathEndPoint = poster_path,
    voteAverage = vote_average
)