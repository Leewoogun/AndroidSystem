package com.lwg.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class GenreListResponse(
    val genres: List<GenreData> = emptyList()
) {
    @Serializable
    data class GenreData(
        val id: Int = 0,
        val name: String = ""
    )
}