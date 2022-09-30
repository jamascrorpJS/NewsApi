package com.jamascrorp.testproject.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context.applicationContext
    }
}