package com.lwg.model.movie

import com.lwg.model.Constants
import com.lwg.util.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Movie(
    val movieId: Int = 0,
    val imageUrlEndPoint: String,
    val title: String,
    @Serializable(with = LocalDateSerializer::class)
    val insertTime: LocalDate = LocalDate.now(),
    val genreList: List<Genre> = emptyList(),
    val isFavorite: Boolean = false
) {
    val imageUrl: String
        get() = Constants.BASE_IMAGE_URL + imageUrlEndPoint
}
