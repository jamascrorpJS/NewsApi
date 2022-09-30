package com.jamascrorp.testproject.presentation.FragmentBookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.domain.usecases.DeleteFromDataBaseUseCase1
import com.jamascrorp.testproject.domain.usecases.GetAllDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookmarksViewModel @Inject constructor(
    private val getAll: GetAllDataUseCase,
    private val deleteFromDataBaseUseCase1: DeleteFromDataBaseUseCase1,
) : ViewModel() {

    fun getAll(): LiveData<List<SavedText>> {
        return getAll.invoke()
    }

    fun delete(savedText: SavedText) {
        viewModelScope.launch {
            deleteFromDataBaseUseCase1.invoke(savedText)
        }
    }

}