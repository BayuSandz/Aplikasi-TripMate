package com.example.tripmate.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tripmate.view_model.AuthViewModel
import com.example.tripmate.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var backgroundVideoView: VideoView
    private lateinit var videoUri: Uri
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        backgroundVideoView = findViewById(R.id.backgroundVideoView)

        videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.latar2)
        backgroundVideoView.setVideoURI(videoUri)

        backgroundVideoView.setOnPreparedListener { mp ->
            val videoRatio = mp.videoWidth.toFloat() / mp.videoHeight.toFloat()
            val screenRatio = backgroundVideoView.width.toFloat() / backgroundVideoView.height.toFloat()
            val scale = videoRatio / screenRatio

            if (scale >= 1f) {
                backgroundVideoView.scaleX = scale
            } else {
                backgroundVideoView.scaleY = 1f / scale
            }
            mp.isLooping = true
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginTextView = findViewById<TextView>(R.id.loginTextView)

        loginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (password.length < 8) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Password harus terdiri minimal 8 karakter",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = authViewModel.registerUser(username, email, password)
                    if (response.isSuccessful) {
                        runOnUiThread {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Daftar berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        navigateToLogin()
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Daftar gagal"
                        runOnUiThread {
                            Toast.makeText(this@RegisterActivity, errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Terjadi kesalahan: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        backgroundVideoView.start()
    }

    override fun onPause() {
        super.onPause()
        backgroundVideoView.stopPlayback()
    }

    fun onLoginClick(view: View) {
        startActivity((Intent(this, LoginActivity::class.java)))
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun togglePasswordVisibility(view: View) {
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val passwordVisibilityToggle = findViewById<ImageView>(R.id.passwordVisibilityToggle)

        if (isPasswordVisible) {
            passwordEditText.transformationMethod =
                android.text.method.PasswordTransformationMethod.getInstance()
            passwordVisibilityToggle.setImageResource(R.drawable.eyec)
        } else {
            passwordEditText.transformationMethod = null
            passwordVisibilityToggle.setImageResource(R.drawable.eyeo)
        }

        passwordEditText.setSelection(passwordEditText.text.length)
        isPasswordVisible = !isPasswordVisible
    }
}
