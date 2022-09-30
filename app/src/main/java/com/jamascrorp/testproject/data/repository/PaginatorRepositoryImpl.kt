package com.jamascrorp.testproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jamascrorp.testproject.data.network.ApiSources
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.data.paging.CategoryPaging
import com.jamascrorp.testproject.data.paging.SearcPaging
import com.jamascrorp.testproject.data.paging.SourcePaging
import com.jamascrorp.testproject.domain.repository.PaginatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaginatorRepositoryImpl @Inject constructor(private val apiSources: ApiSources) : PaginatorRepository {

    override fun getNewsCategor(id: String): Flow<PagingData<Model1>> {
        return Pager(PagingConfig(20)) {
            CategoryPaging(apiSources, id)
        }.flow
    }

    override fun getNewsSource(id: String): Flow<PagingData<Model1>> {
        return Pager(PagingConfig(20)) {
            SourcePaging(apiSources, id)
        }.flow
    }

    override fun getSearch(id: String): Flow<PagingData<Model1>> {
        return Pager(PagingConfig(20)) {
            SearcPaging(apiSources, id)
        }.flow
    }
}