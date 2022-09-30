package com.jamascrorp.testproject.data.network

import com.jamascrorp.testproject.BuildConfig
import com.jamascrorp.testproject.data.models.Sources
import retrofit2.Response
import javax.inject.Inject

class NetworkSourceImpl @Inject constructor(
    private val apiSource: ApiSources,
) : NetworkSource {
    override suspend fun getSources(): Response<Sources> = apiSource.getNews(BuildConfig.API_KEY)
}