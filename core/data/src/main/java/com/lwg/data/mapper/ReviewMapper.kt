package com.lwg.data.mapper

import com.lwg.data.model.MovieReviewsResponse
import com.lwg.model.movie.Author
import com.lwg.model.movie.Review
import com.lwg.model.movie.ReviewInfo

internal fun MovieReviewsResponse.toReview() = Review(
    page = page,
    reviewData = results.map { reviewData ->
        reviewData.toReviewInfo()
    },
    totalPage = total_pages
)

internal fun MovieReviewsResponse.ReviewData.toReviewInfo() = ReviewInfo(
    authorInfo = Author(
        name = author_details.name,
        userName = author_details.username,
        profilePath = author_details.avatar_path
    ),
    content = content,
    rating = author_details.rating ?: 0.0
)