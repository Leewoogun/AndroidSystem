package com.lwg.model.movie

data class Movie(
    val movieId: Int = 0,
    val imageUrlEndPoint: String,
    val title: String,
    val genreList: List<String> = emptyList(),
    val isFavorite: Boolean = false
) {
    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$imageUrlEndPoint"
}
