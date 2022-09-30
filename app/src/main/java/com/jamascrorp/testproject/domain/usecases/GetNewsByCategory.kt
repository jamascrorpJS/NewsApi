package com.jamascrorp.testproject.domain.usecases

import com.jamascrorp.testproject.domain.entity.Categories
import com.jamascrorp.testproject.domain.repository.NewsByCategoryRepository2
import javax.inject.Inject

class GetNewsByCategory @Inject constructor(val repository: NewsByCategoryRepository2) {

    operator fun invoke(): List<Categories> {

        return repository.getAllCategories()
    }
}