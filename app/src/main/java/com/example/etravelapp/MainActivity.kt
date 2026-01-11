package com.example.etravelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.etravelapp.databinding.ActivityMainBinding
import com.example.etravelapp.ui.FavoritesFragment
import com.example.etravelapp.ui.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_LAY_container, HomeFragment())
                .commit()
        }

        binding.mainBottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_LAY_container, HomeFragment())
                        .commit()
                    true
                }

                R.id.nav_favorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_LAY_container, FavoritesFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}
