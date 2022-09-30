package com.jamascrorp.testproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamascrorp.testproject.data.models.Model

@Dao
interface SourcesDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSource(source: List<Model>)

    @Query("DELETE FROM source")
    suspend fun deleteALL()

    @Query("SELECT * FROM source")
    suspend fun getSource() : List<Model>
}