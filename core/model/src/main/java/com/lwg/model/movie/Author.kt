package com.lwg.model.movie

import com.lwg.model.Constants

data class Author(
    val name: String,
    val userName: String,
    val profilePath: String?
) {
    val profileImageUrl: String
        get() = "${Constants.BASE_IMAGE_URL}$profilePath"

}

