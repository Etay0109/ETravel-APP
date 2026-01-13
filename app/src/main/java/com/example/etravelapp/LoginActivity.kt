package com.example.etravelapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.etravelapp.databinding.ActivityLoginBinding
import com.example.etravelapp.utilities.SignalManager
import com.example.etravelapp.utilities.SplashScreenManager
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import androidx.activity.result.contract.ActivityResultContracts



class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var splashManager: SplashScreenManager
    private var isPasswordVisible = false



    // Handles Google Sign-In result and Firebase authentication
    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    val account = task.getResult(Exception::class.java)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: Exception) {
                    splashManager.stop()
                    SignalManager.getInstance().toast("Google sign-in canceled")
                }
            } else {
                splashManager.stop()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashManager = SplashScreenManager(
            overlayView = binding.loginOVERLAYLoading,
            lottieView = binding.loginLOTTIELoading,
            onFinished = {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        )


        auth = FirebaseAuth.getInstance()

        // Create GoogleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        initViews()
        setupClicks()
    }

    private fun initViews() {
        binding.loginETUsername.text?.clear()
        binding.loginETPassword.text?.clear()
    }

    private fun setupClicks() {
        binding.loginBTNSignIn.setOnClickListener {
            onSignInClicked()
        }

        binding.loginBTNSignInGoogle.setOnClickListener {
            signInWithGoogle()
        }

        binding.loginLBLSignUp.setOnClickListener {
            onSignUpClicked()
        }
        binding.loginIMGTogglePassword.setOnClickListener {
            togglePasswordVisibility()
        }
    }
    private fun onSignInClicked() {
        val email = binding.loginETUsername.text.toString().trim()
        val password = binding.loginETPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            SignalManager.getInstance().vibrate()
            SignalManager.getInstance().toast("Please fill all fields")
            return
        }
        //start loading animation
        splashManager.start()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    splashManager.stop()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    splashManager.stop()
                    SignalManager.getInstance().toast("Authentication failed")
                }
            }

    }

    private fun signInWithGoogle() {    // Click on the Google sign-in button
        splashManager.start()
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }


    // Connect to Firebase with the Google account
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                splashManager.stop()
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    SignalManager.getInstance().toast("Google sign-in failed")
                }
            }
    }


    private fun onSignUpClicked() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
    private fun togglePasswordVisibility() {    //switch between the icons
        isPasswordVisible = !isPasswordVisible

        if (isPasswordVisible) {
            binding.loginETPassword.inputType =
                android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.loginIMGTogglePassword.setImageResource(R.drawable.ic_eye_open)
        } else {
            binding.loginETPassword.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.loginIMGTogglePassword.setImageResource(R.drawable.ic_eye_close)
        }

        binding.loginETPassword.setSelection(binding.loginETPassword.text.length)
    }
}


