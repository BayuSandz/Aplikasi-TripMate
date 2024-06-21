package com.example.tripmate.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tripmate.view_model.AuthViewModel
import com.example.tripmate.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var backgroundVideoView: VideoView
    private lateinit var videoUri: Uri
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        backgroundVideoView = findViewById(R.id.backgroundVideoView)

        videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.latar2)
        backgroundVideoView.setVideoURI(videoUri)

        backgroundVideoView.setOnPreparedListener { mp ->
            val videoRatio = mp.videoWidth / mp.videoHeight.toFloat()
            val screenRatio = backgroundVideoView.width / backgroundVideoView.height.toFloat()
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

        val registerTextView = findViewById<TextView>(R.id.registerTextView)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            CoroutineScope(Dispatchers.IO).launch {
                val response = authViewModel.loginUser(email, password)
                if (response.isSuccessful) {
                    navigateToHome()
                } else {
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Email atau password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        backgroundVideoView.setVideoURI(videoUri)
        backgroundVideoView.start()
    }

    override fun onPause() {
        super.onPause()
        backgroundVideoView.stopPlayback()
    }

    fun onRegisterClick(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun togglePasswordVisibility(view: View) {
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val passwordVisibilityToggle = findViewById<ImageView>(R.id.passwordVisibilityToggle)

        if (isPasswordVisible) {
            passwordEditText.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance()
            passwordVisibilityToggle.setImageResource(R.drawable.eyec)
        } else {
            passwordEditText.transformationMethod = null
            passwordVisibilityToggle.setImageResource(R.drawable.eyeo)
        }

        passwordEditText.setSelection(passwordEditText.text.length)
        isPasswordVisible = !isPasswordVisible
    }
}
