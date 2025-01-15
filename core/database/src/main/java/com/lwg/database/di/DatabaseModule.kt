package com.lwg.database.di

import android.content.Context
import androidx.room.Room
import com.lwg.database.LwgDatabase
import com.lwg.database.LwgDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ) : LwgDatabase = Room.databaseBuilder(
        context,
        LwgDatabase::class.java,
        DATABASE_NAME
    ).build()
}