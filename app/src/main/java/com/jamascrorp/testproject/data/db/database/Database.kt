package com.jamascrorp.testproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamascrorp.testproject.data.db.FavoDao
import com.jamascrorp.testproject.data.db.SavedText
import com.jamascrorp.testproject.data.models.Model
import com.jamascrorp.testproject.data.network.Model1


@Database(entities = [Model::class, Model1::class, SavedText::class],
    version = 1,
    exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun favoDao(): FavoDao

    abstract fun sourcesDao(): SourcesDao
}