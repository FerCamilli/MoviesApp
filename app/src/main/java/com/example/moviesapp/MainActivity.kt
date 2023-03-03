package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviesapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentMain) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBarConfiguration()
        setupBottomNavDrawer()
    }

    private fun setupAppBarConfiguration() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_profile,
                R.id.nav_movies,
                R.id.nav_images
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavDrawer() {
        val navView: BottomNavigationView = binding.bottomNavView
        navView.setupWithNavController(navController)
    }
}
