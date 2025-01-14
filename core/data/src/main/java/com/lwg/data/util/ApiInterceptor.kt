package com.lwg.data.util

import com.lwg.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val accessToken = BuildConfig.tmdb_token
        if (accessToken.isNotEmpty()) {
            request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        }
        return chain.proceed(request)
    }
}