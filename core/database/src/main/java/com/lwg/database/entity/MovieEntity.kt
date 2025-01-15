package com.lwg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lwg.database.entity.MovieEntity.Companion.MOVIE_TABLE_NAME

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imageUrlEndPoint: String,
    val title: String,
    val genreList: List<String>,
    val isFavorite: Boolean
) {
    companion object {
        const val MOVIE_TABLE_NAME = "movie_table"
    }
}
