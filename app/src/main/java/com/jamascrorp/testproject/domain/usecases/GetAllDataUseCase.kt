package com.jamascrorp.testproject.domain.usecases

import androidx.lifecycle.LiveData
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.domain.repository.SaveToDataBaseRepository
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(val repository: SaveToDataBaseRepository) {

    operator fun invoke(): LiveData<List<SavedText>> {
        return repository.getAll()
    }
}