package com.jamascrorp.testproject.data.network

import androidx.annotation.IntRange
import com.jamascrorp.testproject.data.models.Sources
import com.jamascrorp.testproject.data.models.Sources1
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSources {
    @GET("top-headlines/sources")
    suspend fun getNews(@Query("apiKey") key: String): Response<Sources>

    @GET("top-headlines")
    suspend fun getSourceNews(
        @Query("sources") id: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("language") id1: String = "en",
        @Query("pageSize") @IntRange(from = 1, to = MAX_SIZE.toLong()) pageSize: Int = 1,
        @Query("apiKey") key: String,
    ): Response<Sources1>

    @GET("top-headlines")
    suspend fun getCategoryNews(
        @Query("category") id: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("language") id1: String = "en",
        @Query("pageSize") @IntRange(from = 1, to = MAX_SIZE.toLong()) pageSize: Int = 1,
        @Query("apiKey") key: String,
    ): Response<Sources1>

    @GET("everything")
    suspend fun getNewsSearch(
        @Query("q") id: String,
        @Query("page") page: Int = 1,
        @Query("language") id1: String = "en",
        @Query("pageSize") @IntRange(from = 1, to = MAX_SIZE1.toLong()) pageSize: Int = 100,
        @Query("apiKey") key: String,
    ): Response<Sources1>

    companion object {
        const val MAX_SIZE = 10
        const val MAX_SIZE1 = 100
    }
}