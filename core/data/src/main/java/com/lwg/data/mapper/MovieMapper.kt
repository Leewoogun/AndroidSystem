package com.lwg.data.mapper

import com.lwg.data.model.TopRatedMovieResponse
import com.lwg.model.movie.Movie

internal fun TopRatedMovieResponse.MovieData.toMovie() : Movie = Movie(
    imageUrlEndPoint = poster_path,
    title = title,
)

