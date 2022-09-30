package com.jamascrorp.testproject.domain.usecases

import com.jamascrorp.testproject.data.models.Model
import com.jamascrorp.testproject.domain.repository.SourcesRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(private val repository: SourcesRepository) {

    suspend operator fun invoke(): List<Model> = repository.getSources()
}