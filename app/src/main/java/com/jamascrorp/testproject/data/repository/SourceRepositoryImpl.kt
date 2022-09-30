package com.jamascrorp.testproject.data.repository

import android.util.Log
import com.jamascrorp.testproject.data.models.Model
import com.jamascrorp.testproject.data.network.NetworkSource
import com.jamascrorp.testproject.domain.repository.DatabaseDataSource
import com.jamascrorp.testproject.domain.repository.SourcesRepository
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val databaseDataSource: DatabaseDataSource,
    private val networkSource: NetworkSource,
) : SourcesRepository {

    override suspend fun getSources(): List<Model> {
        return getSourceFromDataBase()!!
    }

    suspend fun getSourceFromNetwork(): List<Model>? {
        var model: List<Model>? = null
        try {
            val response = networkSource.getSources()
            val body = response.body()
            model = requireNotNull(body?.sources)
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return model
    }


    suspend fun getSourceFromDataBase(): List<Model>{
        var model1: List<Model>? = null
        model1 = getSourceFromNetwork()
        if (model1 == null) {
            model1 = databaseDataSource.getSource()
        } else {
            databaseDataSource.deleteAll()
            databaseDataSource.saveSource(model1)
            model1 = databaseDataSource.getSource()
        }
        return model1
    }
}