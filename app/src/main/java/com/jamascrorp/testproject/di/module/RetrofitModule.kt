package com.jamascrorp.testproject.di.module

import com.jamascrorp.testproject.data.network.ApiSources
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule(private val baseUrl: String) {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ApiSources {
        return retrofit.create(ApiSources::class.java)
    }
}