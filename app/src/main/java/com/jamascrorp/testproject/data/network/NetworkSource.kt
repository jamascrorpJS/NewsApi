package com.jamascrorp.testproject.data.network

import com.jamascrorp.testproject.data.models.Sources
import retrofit2.Response

interface NetworkSource {

    suspend fun getSources(): Response<Sources>
}