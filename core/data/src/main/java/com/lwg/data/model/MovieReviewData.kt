package com.lwg.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class MovieReviewsResponse(
    val page: Int,
    val results: List<ReviewData>,
    val total_pages: Int
) {
    @Serializable
    data class ReviewData(
        val author: String,
        val author_details: AuthorDetailData,
        val content: String,
    )
}

@Serializable
internal data class AuthorDetailData(
    val name: String,
    val username: String,
    val avatar_path: String,
    val rating: Double
)
