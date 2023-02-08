package com.example.imsapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.imsapp.R
import com.example.imsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()

    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.apply {
            val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navHostFragment.navController.apply {
                setupWithNavController(this)
                addOnDestinationChangedListener { _, _, args ->
                    // Top level items should have such argument with value set to true
                    isVisible = args?.getBoolean("hasBottomNav", false) == true
                }
                setOnItemReselectedListener {

                    // Do nothing when selecting same item
                }
                // Set the bottom navigation view/bar background color

            }
        }
    }
}