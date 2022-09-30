package com.jamascrorp.testproject.data.repository

import android.content.Context
import com.jamascrorp.testproject.R
import com.jamascrorp.testproject.domain.entity.Categories
import com.jamascrorp.testproject.domain.repository.NewsByCategoryRepository2
import javax.inject.Inject

class NewsByCategoryRepositoryImpl1 @Inject constructor(context: Context) :
    NewsByCategoryRepository2 {

    val list: List<Categories> = listOf(
        Categories("general", context?.getColor(R.color.general)!!),
        Categories("business", context?.getColor(R.color.business)!!),
        Categories("science", context?.getColor(R.color.science)!!),
        Categories("technology", context?.getColor(R.color.technology)!!),
        Categories("health", context?.getColor(R.color.health)!!),
        Categories("entertainment", context?.getColor(R.color.entertainment)!!),
        Categories("sport", context?.getColor(R.color.sport)!!)
    )

    override fun getAllCategories(): List<Categories> {
        return list
    }
}