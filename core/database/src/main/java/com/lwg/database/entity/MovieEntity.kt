package com.lwg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lwg.database.entity.MovieEntity.Companion.MOVIE_TABLE_NAME
import com.lwg.model.movie.Genre

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val imageUrlEndPoint: String,
    val title: String,
    val genreList: List<Genre>,
    val isFavorite: Boolean
) {
    companion object {
        const val MOVIE_TABLE_NAME = "movie_table"
    }
}
