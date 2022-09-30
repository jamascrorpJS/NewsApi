package com.jamascrorp.testproject.domain.repository

import androidx.lifecycle.LiveData
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.data.network.Model1

interface SaveToDataBaseRepository {

    suspend fun addText(savedText: SavedText, model: Model1)

    fun getAll(): LiveData<List<SavedText>>

    suspend fun delete(url: String)
    suspend fun delete1(savedText: SavedText)
    suspend fun check(url: String): Boolean
}