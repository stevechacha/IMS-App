package com.example.imsapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.imsapp.R
import com.example.imsapp.cart.CartFragment
import com.example.imsapp.databinding.ActivityMainBinding
import com.example.imsapp.home.HomeFragment
import com.example.imsapp.messages.MessagesFragment
import com.example.imsapp.profile.ProfileFragment
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
        val homeFragment = HomeFragment()
        val cartFragment= CartFragment()
        val messageFragment= MessagesFragment()
        val profileFragment= ProfileFragment()
        setCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnItemReselectedListener{
            when(it.itemId){
                R.id.homeFragment ->setCurrentFragment(homeFragment)
                R.id.cartFragment ->setCurrentFragment(cartFragment)
                R.id.messagesFragment ->setCurrentFragment(messageFragment)
                R.id.profileFragment ->setCurrentFragment(profileFragment)
            }
            return@setOnItemReselectedListener
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment,fragment)
            commit()
        }
}