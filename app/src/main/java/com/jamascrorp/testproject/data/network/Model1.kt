package com.jamascrorp.testproject.data.network

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jamascrorp.testproject.data.models.Source
import kotlinx.parcelize.Parcelize

@Entity(tableName = "seconds")
@Parcelize
data class Model1(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @Embedded(prefix = "column")
    var source: Source? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    @SerializedName("urlToImage")
    var image: String? = null,
    @SerializedName("publishedAt")
    var dataPublic: String? = null,
    var content: String? = null,
) : Parcelable {
    override fun hashCode(): Int {
        var result = id.hashCode()
        if (url.isNullOrEmpty()) {
            result = 31 * result + url.hashCode()
        }
        return result
    }
}