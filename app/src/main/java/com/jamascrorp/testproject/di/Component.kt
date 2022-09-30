package com.jamascrorp.testproject.di

import com.jamascrorp.testproject.di.module.*
import com.jamascrorp.testproject.presentation.FragmentAccount.CreateProfileFragment
import com.jamascrorp.testproject.presentation.FragmentBookmarks.BookmarksFragment
import com.jamascrorp.testproject.presentation.FragmentCategory.CategoryFragment
import com.jamascrorp.testproject.presentation.FragmentCategoryNews.CategoryNewsFragment
import com.jamascrorp.testproject.presentation.FragmentSources.SourcesFragment
import com.jamascrorp.testproject.presentation.WebView.WebViewFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, ContextModule::class, AppModule::class, DatabaseModule::class, RetrofitModule::class])
interface Component {

    fun injectCategory(fragment: CategoryFragment)

    fun injectNews(fragment: CategoryNewsFragment)

    fun injectBookmarks(fragment: BookmarksFragment)

    fun inject(fragment: SourcesFragment)

    fun inject(fragment: CreateProfileFragment)

    fun inject(fragment: WebViewFragment)

}