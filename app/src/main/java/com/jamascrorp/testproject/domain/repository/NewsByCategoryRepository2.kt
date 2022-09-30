package com.jamascrorp.testproject.domain.repository

import com.jamascrorp.testproject.domain.entity.Categories

interface NewsByCategoryRepository2 {

    fun getAllCategories(): List<Categories>
}