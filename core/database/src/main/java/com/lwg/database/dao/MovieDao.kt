package com.lwg.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.lwg.database.LwgDatabase
import com.lwg.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MovieDao {
    @Upsert
    suspend fun upsertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM ${LwgDatabase.DATABASE_NAME}")
    fun getMovies() : Flow<List<MovieEntity>>

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)
}