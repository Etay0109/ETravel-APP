package com.example.etravelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.etravelapp.ui.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the first fragment only once
        if (savedInstanceState == null) {

            val startFragment =
                if (FirebaseAuth.getInstance().currentUser == null) {
                    HomeFragment()
                } else {
                    HomeFragment()
                }

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_LAY_container, startFragment)
                .commit()
        }
    }
}
