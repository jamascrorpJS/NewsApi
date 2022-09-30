package com.jamascrorp.testproject.presentation.FragmentCategoryNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.domain.usecases.GetNewsByCategoryUseCase
import com.jamascrorp.testproject.domain.usecases.GetNewsBySourceUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryNewsFragmentViewModel @Inject constructor(
    private val getNewsBySource: GetNewsBySourceUseCase,
    private val getNewsByCategory: GetNewsByCategoryUseCase,
) : ViewModel() {

    fun getnewsByCategory(id: String): Flow<PagingData<Model1>> {
        return getNewsByCategory(id).cachedIn(viewModelScope)
    }

    fun getnewsBySource(id: String): Flow<PagingData<Model1>> {
        return getNewsBySource(id).cachedIn(viewModelScope)
    }
}