package com.lwg.database.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListTypeConverter {

    private val json = Json { encodeDefaults = true }

    @TypeConverter
    fun fromList(value: List<String>?): String? {
        return value?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toList(value: String?): List<String>? {
        return value?.let { json.decodeFromString(it) }
    }
}
