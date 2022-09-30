package com.jamascrorp.testproject.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "saved_text")
data class SavedText(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var author: String = "",
    var title: String = "",
    var url: String = "",
    @ColumnInfo(name = "url_to_image")
    var urlToImage: String = "",
)