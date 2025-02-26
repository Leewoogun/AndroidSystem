package com.lwg.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lwg.database.LwgDatabase.Companion.DATABASE_VERSION
import com.lwg.database.converter.GenreTypeConverter
import com.lwg.database.converter.ListTypeConverter
import com.lwg.database.dao.MovieDao
import com.lwg.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = true,
)
@TypeConverters(
    value = [
        ListTypeConverter::class,
        GenreTypeConverter::class
    ]
)
internal abstract class LwgDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "lwg_database"
        const val DATABASE_VERSION = 1
    }
}