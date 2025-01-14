package com.lwg.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lwg.data.util.ApiInterceptor
import com.lwg.data.util.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideInterceptorOkHttpClient(
        authInterceptor: ApiInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpNetworkLogger())
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
    }

    @Provides
    @Singleton
    fun provideConverterFactory(json: Json): Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(ResultCallAdapterFactory.Companion.create())
            .addConverterFactory(converterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
}