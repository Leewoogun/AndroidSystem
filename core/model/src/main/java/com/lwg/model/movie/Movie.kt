package com.lwg.model.movie

import com.lwg.model.Constants

data class Movie(
    val movieId: Int = 0,
    val imageUrlEndPoint: String,
    val title: String,
    val genreList: List<String> = emptyList(),
    val isFavorite: Boolean = false
) {
    val imageUrl: String
        get() = Constants.BASE_IMAGE_URL + imageUrlEndPoint
}
