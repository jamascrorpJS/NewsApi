package com.jamascrorp.testproject.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return Application()
    }
}