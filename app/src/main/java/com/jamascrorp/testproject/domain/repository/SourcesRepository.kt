package com.jamascrorp.testproject.domain.repository

import com.jamascrorp.testproject.data.models.Model

interface SourcesRepository {

    suspend fun getSources(): List<Model>
}