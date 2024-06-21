package com.example.cobaapi2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.cobaapi2.view_model.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Panggil fungsi registerUser dari ViewModel
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = authViewModel.registerUser(username, email, password)
                    if (response.isSuccessful) {
                        // Registrasi berhasil
                        runOnUiThread {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registrasi berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        navigateToLogin()
                    } else {
                        // Registrasi gagal, tampilkan pesan kesalahan dari server
                        val errorMessage = response.errorBody()?.string() ?: "Registrasi gagal"
                        runOnUiThread {
                            Toast.makeText(this@RegisterActivity, errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (e: Exception) {
                    // Tangani exception saat terjadi kesalahan koneksi atau lainnya
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

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }
}