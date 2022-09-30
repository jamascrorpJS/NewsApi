package com.jamascrorp.testproject.data.repository

import androidx.lifecycle.LiveData
import com.jamascrorp.testproject.data.db.FavoDao
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.data.network.Model1
import com.jamascrorp.testproject.domain.repository.SaveToDataBaseRepository
import javax.inject.Inject

class SaveToDataBaseRepositoryImpl @Inject constructor(val favoDao: FavoDao) :
    SaveToDataBaseRepository {

    override suspend fun addText(savedText: SavedText, model: Model1) {
        savedText.url = model.url ?: ""
        savedText.title = model.title ?: ""
        savedText.author = model.author ?: ""
        savedText.urlToImage = model.image ?: ""
        favoDao.createData(savedText)
    }

    override fun getAll(): LiveData<List<SavedText>> {
        return favoDao.getAll()
    }

    override suspend fun delete(url: String) {
        return favoDao.deleteText(url)
    }

    override suspend fun delete1(savedText: SavedText) {
        return favoDao.delete(savedText)
    }

    override suspend fun check(url: String): Boolean {
        return favoDao.check(url)
    }
}