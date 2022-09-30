package com.jamascrorp.testproject.di.module

import android.content.Context
import androidx.room.Room
import com.jamascrorp.testproject.data.database.DataBase
import com.jamascrorp.testproject.data.database.SourcesDao
import com.jamascrorp.testproject.data.db.FavoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, "database")
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoDao(database: DataBase): FavoDao {
        return database.favoDao()
    }

    @Singleton
    @Provides
    fun provideSourcesDao(database: DataBase): SourcesDao {
        return database.sourcesDao()
    }
}