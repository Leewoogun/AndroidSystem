package com.lwg.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDetailResponse(
    val original_title: String = "",
    val overview: String = "",
    val poster_path: String = "",
    val vote_average: Double = 0.0,
)