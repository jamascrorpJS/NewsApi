package com.jamascrorp.testproject.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jamascrorp.testproject.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.category_toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.categoryFragment,
            R.id.sourcesFragment,
            R.id.bookmarksFragment,
            R.id.authorizationFragment))
        toolbar.setupWithNavController(navController, appBarConfiguration)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom)
        bottomNavView.setupWithNavController(navController)
    }
}