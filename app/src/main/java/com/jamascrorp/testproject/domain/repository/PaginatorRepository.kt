package com.jamascrorp.testproject.domain.repository

import androidx.paging.PagingData
import com.jamascrorp.testproject.data.network.Model1
import kotlinx.coroutines.flow.Flow

interface PaginatorRepository {


    fun getNewsCategor(id: String): Flow<PagingData<Model1>>
    fun getNewsSource(id: String): Flow<PagingData<Model1>>
    fun getSearch(id: String): Flow<PagingData<Model1>>
}