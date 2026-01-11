package com.example.etravelapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.etravelapp.databinding.ActivityRegisterBinding
import com.example.etravelapp.utilities.SignalManager
import com.example.etravelapp.utilities.SplashScreenManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var splashManager: SplashScreenManager

    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init loading animation manager
        splashManager = SplashScreenManager(
            overlayView = binding.registerOVERLAYLoading,
            lottieView = binding.registerLOTTIELoading,
            onFinished = {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        )


        auth = FirebaseAuth.getInstance()
        initViews()
        setupClicks()
    }
    private fun initViews() {
        binding.registerETUsername.text?.clear()
        binding.registerETEmail.text?.clear()
        binding.registerETPassword.text?.clear()
        binding.registerETConfirmPassword.text?.clear()
    }

    private fun setupClicks() {
        binding.registerBTNSubmit.setOnClickListener {
            onRegisterClicked()
        }

        binding.registerIMGTogglePassword.setOnClickListener {
            togglePasswordVisibility()
        }

        binding.registerIMGToggleConfirmPassword.setOnClickListener {
            toggleConfirmPasswordVisibility()
        }
    }
    private fun onRegisterClicked() {

        val username = binding.registerETUsername.text.toString().trim()
        val email = binding.registerETEmail.text.toString().trim()
        val password = binding.registerETPassword.text.toString().trim()
        val confirmPassword = binding.registerETConfirmPassword.text.toString().trim()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            SignalManager.getInstance().vibrate()
            SignalManager.getInstance().toast("Please fill all fields")
            return
        }

        if (password != confirmPassword) {
            SignalManager.getInstance().vibrate()
            SignalManager.getInstance().toast("Passwords do not match")
            return
        }

        if (password.length < 6) {
            SignalManager.getInstance().vibrate()
            SignalManager.getInstance().toast("Password must be at least 6 characters")
            return
        }
        //start loading animation
        splashManager.start()

        // Register the user + save username
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = username
                    }

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener {
                        splashManager.stop()
                    }

                } else {
                    splashManager.stop()
                    SignalManager.getInstance().vibrate()
                    SignalManager.getInstance().toast("Registration failed.")
                }
            }

    }


    private fun togglePasswordVisibility() {    //switch between the icons
        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            binding.registerETPassword.inputType =
                android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.registerIMGTogglePassword.setImageResource(R.drawable.ic_eye_open)
        } else {
            binding.registerETPassword.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.registerIMGTogglePassword.setImageResource(R.drawable.ic_eye_close)
        }

        binding.registerETPassword.setSelection(binding.registerETPassword.text.length)
    }
    private fun toggleConfirmPasswordVisibility() { //switch between the icons
        isConfirmPasswordVisible = !isConfirmPasswordVisible

        if (isConfirmPasswordVisible) {
            binding.registerETConfirmPassword.inputType =
                android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.registerIMGToggleConfirmPassword
                .setImageResource(R.drawable.ic_eye_open)
        } else {
            binding.registerETConfirmPassword.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.registerIMGToggleConfirmPassword
                .setImageResource(R.drawable.ic_eye_close)
        }

        binding.registerETConfirmPassword.setSelection(
            binding.registerETConfirmPassword.text.length
        )
    }

}

