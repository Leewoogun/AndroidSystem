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

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(
        page: Int,
        onError: (String) -> Unit
    ): Flow<List<Movie>> {
        return movieRepository.getTopRatedMovies(page, onError)
            .flatMapLatest { movies ->
                flow {
                    val favoriteMovies = movieRepository.getFavoriteMovie()

                    val updatedMovies = movies.map { movie ->
                        val favorite = favoriteMovies.find { it.movieId == movie.movieId }
                        favorite?.let {
                            movie.copy(isFavorite = it.isFavorite)
                        } ?: movie.copy(isFavorite = false)
                    }
                    emit(updatedMovies)
                }
            }
    }
}