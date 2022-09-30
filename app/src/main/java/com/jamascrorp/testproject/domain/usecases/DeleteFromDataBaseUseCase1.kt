package com.jamascrorp.testproject.domain.usecases

import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.domain.repository.SaveToDataBaseRepository
import javax.inject.Inject

class DeleteFromDataBaseUseCase1 @Inject constructor(val repository: SaveToDataBaseRepository) {

    suspend operator fun invoke(savedText: SavedText) {
        repository.delete1(savedText)
    }
}