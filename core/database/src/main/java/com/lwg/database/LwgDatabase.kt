package com.lwg.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lwg.database.LwgDatabase.Companion.DATABASE_VERSION
import com.lwg.database.converter.GenreTypeConverter
import com.lwg.database.converter.ListTypeConverter
import com.lwg.database.converter.LocalDateConverter
import com.lwg.database.dao.MovieDao
import com.lwg.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = DATABASE_VERSION,
    autoMigrations = [
        AutoMigration(
            from = 1, to = 2,
            spec = DatabaseMigrations.Schema1to2::class
        )
    ],
    exportSchema = true,
)
@TypeConverters(
    value = [
        ListTypeConverter::class,
        GenreTypeConverter::class,
        LocalDateConverter::class
    ]
)
internal abstract class LwgDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "lwg_database"
        const val DATABASE_VERSION = 2
    }
}