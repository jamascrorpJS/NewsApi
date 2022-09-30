package com.jamascrorp.testproject.domain.usecases

import androidx.paging.PagingData
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.domain.repository.PaginatorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchNewsUseCase @Inject constructor(private val repository: PaginatorRepository) {

    operator fun invoke(id:String) : Flow<PagingData<Model1>> {
        return repository.getSearch(id)
    }
}