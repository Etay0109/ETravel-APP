package com.example.etravelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.etravelapp.ui.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_LAY_container, HomeFragment())
                .commit()
        }
    }
}
