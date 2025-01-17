package com.lwg.data.mapper

import com.lwg.data.model.GenreListResponse
import com.lwg.model.movie.Genre

internal fun GenreListResponse.GenreData.toGenre() : Genre = Genre(
    id = id,
    name = name
)