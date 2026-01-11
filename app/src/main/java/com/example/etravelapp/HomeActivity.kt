package com.example.etravelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.etravelapp.databinding.ActivityHomeBinding
import com.example.etravelapp.ui.HomeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_FRAME_container, HomeFragment())
                .commit()
        }
    }
}

