package com.lwg.data.mapper

import com.lwg.data.model.TopRatedMovieResponse
import com.lwg.database.entity.MovieEntity
import com.lwg.model.movie.Genre
import com.lwg.model.movie.Movie

internal fun TopRatedMovieResponse.MovieData.toMovie(genre: List<Genre>) : Movie = Movie(
    movieId = id,
    imageUrlEndPoint = poster_path,
    title = title,
    genreList = genre.filter { it.id in genre_ids },
)

internal fun MovieEntity.toMovie(): Movie = Movie(
    movieId = id,
    imageUrlEndPoint = imageUrlEndPoint,
    title = title,
    genreList = genreList,
    isFavorite = isFavorite
)

internal fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
    id = movieId,
    imageUrlEndPoint = imageUrlEndPoint,
    title = title,
    genreList = genreList,
    isFavorite = isFavorite
)

