package com.lwg.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.lwg.database.LwgDatabase
import com.lwg.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM ${MovieEntity.MOVIE_TABLE_NAME}")
    fun getMovies() : Flow<List<MovieEntity>>

    @Query("DELETE FROM ${MovieEntity.MOVIE_TABLE_NAME} WHERE id = :id")
    suspend fun deleteMovie(id: Int)
}