package com.lwg.model.movie

data class Movie(
    val imageUrlEndPoint: String,
    val title: String,
    val genreList: List<String> = emptyList(),
    val isFavorite: Boolean = false
) {
    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$imageUrlEndPoint"
}
