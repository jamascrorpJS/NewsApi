package com.jamascrorp.testproject.data.repository

import com.jamascrorp.testproject.domain.repository.DatabaseDataSource
import com.jamascrorp.testproject.data.database.SourcesDao
import com.jamascrorp.testproject.data.models.Model
import javax.inject.Inject

class DatabaseDataSourceImpl @Inject constructor(private val sourcesDao: SourcesDao) :
    DatabaseDataSource {

    override suspend fun saveSource(source: List<Model>) = sourcesDao.saveSource(source)

    override suspend fun deleteAll() = sourcesDao.deleteALL()

    override suspend fun getSource(): List<Model> = sourcesDao.getSource()
}