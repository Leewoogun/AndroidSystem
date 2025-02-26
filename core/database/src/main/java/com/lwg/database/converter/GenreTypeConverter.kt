package com.lwg.database.converter

import androidx.room.TypeConverter
import com.lwg.model.movie.Genre
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GenreTypeConverter {
    private val json = Json { encodeDefaults = true }

    @TypeConverter
    fun fromGenreList(genreList: List<Genre>): String {
        return json.encodeToString(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListString: String): List<Genre> {
        return json.decodeFromString(genreListString)
    }
}