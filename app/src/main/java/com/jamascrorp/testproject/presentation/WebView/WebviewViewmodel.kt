package com.jamascrorp.testproject.presentation.WebView

import androidx.lifecycle.ViewModel
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.domain.usecases.DeleteFromDataBaseUseCase
import com.jamascrorp.testproject.domain.usecases.ExistsTextDataBaseUseCase
import com.jamascrorp.testproject.domain.usecases.SaveToDataBaseUseCase
import javax.inject.Inject

class WebviewViewmodel @Inject constructor(
    private val save: SaveToDataBaseUseCase,
    private val existsTextDataBaseUseCase: ExistsTextDataBaseUseCase,
    private val delete: DeleteFromDataBaseUseCase,
) : ViewModel() {
    suspend fun save(model: Model1, savedText: SavedText) {
        save.invoke(model, savedText)
    }

    suspend fun delete(url: String) {
        delete.invoke(url)
    }

    suspend fun exists(url: String): Boolean {
        return existsTextDataBaseUseCase.invoke(url)
    }
}
