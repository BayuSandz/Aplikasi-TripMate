package com.example.tripmate.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tripmate.R
import com.example.tripmate.databinding.ActivityPlaceDetailBinding

class PlaceDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        val placeName = intent.getStringExtra("place_name")
        val placeDescription = intent.getStringExtra("place_description")
        val placeImage = intent.getIntExtra("place_image", R.drawable.default_image)
        val placeRating = intent.getDoubleExtra("place_rating", 0.0)
        val placeHargaMasuk = intent.getStringExtra("place_harga") ?: "Harga tidak tersedia"

        binding.placeName.text = placeName
        binding.placeDescription.text = placeDescription
        binding.placeImage.setImageResource(placeImage)
        binding.placeRating.text = "Rating: $placeRating"
        binding.placeHargaMasuk.text = "Harga Masuk: $placeHargaMasuk"
    }
}
