package com.lwg.model.movie

import com.lwg.model.Constants

data class MovieDetail(
    val movieTitle: String,
    val overview: String,
    val posterPathEndPoint: String,
    val voteAverage: Double
) {
    val posterImageUrl: String
        get() = Constants.BASE_IMAGE_URL + posterPathEndPoint
}
