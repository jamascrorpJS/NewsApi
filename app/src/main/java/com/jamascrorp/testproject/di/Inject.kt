package com.jamascrorp.testproject.di

import android.app.Application
import com.jamascrorp.testproject.di.module.ContextModule
import com.jamascrorp.testproject.di.module.RetrofitModule
import com.jamascrorp.testproject.presentation.FragmentAccount.CreateProfileFragment
import com.jamascrorp.testproject.presentation.FragmentBookmarks.BookmarksFragment
import com.jamascrorp.testproject.presentation.FragmentCategory.CategoryFragment
import com.jamascrorp.testproject.presentation.FragmentCategoryNews.CategoryNewsFragment
import com.jamascrorp.testproject.presentation.FragmentSources.SourcesFragment
import com.jamascrorp.testproject.presentation.WebView.WebViewFragment

class Inject: Application(), Component {

    private lateinit var component: Component
    override fun onCreate() {
        super.onCreate()
        component = DaggerComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .retrofitModule(RetrofitModule("https://newsapi.org/v2/"))
            .build()
    }

    override fun injectCategory(fragment: CategoryFragment) {
        component.injectCategory(fragment)
    }

    override fun injectNews(fragment: CategoryNewsFragment) {
        component.injectNews(fragment)
    }

    override fun injectBookmarks(fragment: BookmarksFragment) {
        component.injectBookmarks(fragment)
    }
    override fun inject(fragment: CreateProfileFragment) {

        component.inject(fragment)
    }

    override fun inject(fragment: SourcesFragment) {
        component.inject(fragment)
    }


    override fun inject(fragment: WebViewFragment) {
        component.inject(fragment)
    }
}