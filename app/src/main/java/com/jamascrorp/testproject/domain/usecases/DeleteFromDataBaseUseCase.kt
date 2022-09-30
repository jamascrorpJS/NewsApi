package com.jamascrorp.testproject.domain.usecases

import com.jamascrorp.testproject.domain.repository.SaveToDataBaseRepository
import javax.inject.Inject

class DeleteFromDataBaseUseCase @Inject constructor(val repository: SaveToDataBaseRepository) {

    suspend operator fun invoke(url: String) {
        repository.delete(url)
    }
}