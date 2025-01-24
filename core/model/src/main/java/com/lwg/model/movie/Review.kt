package com.lwg.model.movie

data class Review(
    val page: Int,
    val reviewData: List<ReviewInfo>,
    val totalPage: Int
)

data class ReviewInfo(
    val authorInfo: Author,
    val content: String,
    val rating: Double
)
