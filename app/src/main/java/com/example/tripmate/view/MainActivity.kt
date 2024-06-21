package com.example.tripmate.view


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ViewFlipper
import androidx.core.content.ContextCompat
import com.example.tripmate.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewFlipper = findViewById<ViewFlipper>(R.id.viewFlipper)
        viewFlipper.flipInterval = 2000
        viewFlipper.isAutoStart = true
        viewFlipper.inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        viewFlipper.outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}