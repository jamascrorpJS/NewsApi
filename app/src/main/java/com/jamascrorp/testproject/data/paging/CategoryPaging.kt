package com.jamascrorp.testproject.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jamascrorp.testproject.BuildConfig
import com.jamascrorp.testproject.data.network.ApiSources
import com.jamascrorp.testproject.data.network.Model1


class CategoryPaging(private val apiSources: ApiSources, private val id: String) :
    PagingSource<Int, Model1>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Model1> {
        try {
            val currentList = params.key ?: 1
            val sizes = params.loadSize.coerceAtMost(ApiSources.MAX_SIZE)
            val response = apiSources.getCategoryNews(id, currentList, "en", sizes, BuildConfig.API_KEY)

            val sl = mutableListOf<Model1>()
            val body = response.body()?.articles
            sl.addAll(body!!)
            val nextKey = if (sl.size < sizes) null else currentList + 1
            val prevKey = if (currentList == 1) null else currentList - 1
            return LoadResult.Page(
                body, prevKey, nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Model1>): Int? {
       return state.anchorPosition
    }
}