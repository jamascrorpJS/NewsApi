package com.jamascrorp.testproject.domain.usecases

import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.domain.repository.SaveToDataBaseRepository
import javax.inject.Inject

class SaveToDataBaseUseCase @Inject constructor(val repository: SaveToDataBaseRepository) {

    suspend operator fun invoke(model: Model1, savedText: SavedText) {
        repository.addText(savedText, model)
    }
}