package com.jamascrorp.testproject.domain.repository

import com.jamascrorp.testproject.data.models.Model

interface DatabaseDataSource {

    suspend fun saveSource(source: List<Model>)
    suspend fun deleteAll()

    suspend fun getSource(): List<Model>
}