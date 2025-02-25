package com.lwg.domain

import com.lwg.data_api.MovieRepository
import com.lwg.model.movie.Movie
import com.lwg.util.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(
        page: Int,
        onError: (String) -> Unit
    ): Flow<List<Movie>> {
        return combine(
            movieRepository.getTopRatedMovies(page, onError),
            movieRepository.getFavoriteMovie()
        ) { movies, favoriteMovies ->
            val updatedMovies = movies.map { movie ->
                val favorite = favoriteMovies.find { it.movieId == movie.movieId }
                favorite?.let {
                    movie.copy(isFavorite = it.isFavorite)
                } ?: movie.copy(isFavorite = false)
            }
            updatedMovies
        }
    }
}