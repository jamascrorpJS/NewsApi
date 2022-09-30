package com.jamascrorp.testproject.presentation.FragmentCategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jamascrorp.testproject.data.network.ApiSources
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.data.paging.SearcPaging
import com.jamascrorp.testproject.domain.entity.Categories
import com.jamascrorp.testproject.domain.usecases.GetNewsByCategory
import com.jamascrorp.testproject.domain.usecases.GetSearchNewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryFragmentViewModel @Inject constructor(

    private val getCategory: GetNewsByCategory,
    private val getSearch: GetSearchNewsUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<List<Categories>>()
    val categoryLiveData = liveData

    fun getCategories() {
        categoryLiveData.postValue(getCategory.invoke())
    }

    fun getNews(id: String): Flow<PagingData<Model1>> {
        return getSearch.invoke(id).cachedIn(viewModelScope)
    }
}