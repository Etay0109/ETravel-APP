package com.example.etravelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.etravelapp.databinding.ActivityMainBinding
import com.example.etravelapp.ui.FavoritesFragment
import com.example.etravelapp.ui.HomeFragment
import com.example.etravelapp.ui.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val favoritesFragment = FavoritesFragment()
    private val profileFragment = ProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_LAY_container, homeFragment)
                .commit()
        }


        binding.mainBottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> homeFragment
                R.id.nav_favorites -> favoritesFragment
                R.id.nav_profile -> profileFragment
                else -> homeFragment
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_LAY_container, fragment)
                .commit()

            true
        }

    }
}
