package com.jamascrorp.testproject.data.models

import androidx.room.Entity


@Entity(tableName = "source", primaryKeys = ["id", "name"])
data class Model(

    var id: String,
    var name: String,
    var category: String? = null,
    var description: String? = null,
    var url: String? = null,
    var language: String? = null,
    var country: String? = null,
)
