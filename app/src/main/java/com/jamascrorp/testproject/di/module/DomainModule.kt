package com.jamascrorp.testproject.di.module

import com.jamascrorp.testproject.data.network.NetworkSource
import com.jamascrorp.testproject.data.network.NetworkSourceImpl
import com.jamascrorp.testproject.data.repository.*
import com.jamascrorp.testproject.domain.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Singleton
    @Binds
    fun bindAuthorizationRepository(impl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Singleton
    @Binds
    fun bindNewsByCategRepository1(impl: NewsByCategoryRepositoryImpl1): NewsByCategoryRepository2

    @Singleton
    @Binds
    fun bindSaveToDataBaseRepository(impl: SaveToDataBaseRepositoryImpl): SaveToDataBaseRepository

    @Singleton
    @Binds
    fun bindPaginatorRepository(impl: PaginatorRepositoryImpl): PaginatorRepository

    @Singleton
    @Binds
    fun bindSourceRepositoryImpl(impl: SourceRepositoryImpl): SourcesRepository

    @Singleton
    @Binds
    fun bindNetworks(impl: NetworkSourceImpl): NetworkSource

    @Singleton
    @Binds
    fun bindDatabases(impl: DatabaseDataSourceImpl): DatabaseDataSource
}