package com.jamascrorp.testproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface FavoDao {

    @Insert(onConflict = REPLACE)
    suspend fun createData(savedText: SavedText)

    @Query("DELETE FROM saved_text WHERE url=:url")
    suspend fun deleteText(url: String)

    @Delete()
    suspend fun delete(savedText: SavedText)

    @Query("SELECT * FROM saved_text ORDER BY id DESC")
    fun getAll(): LiveData<List<SavedText>>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_text WHERE url=:url)")
    suspend fun check(url: String) : Boolean
}