package com.example.tripmate.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.R
import com.example.tripmate.adapter.PlaceAdapter
import com.example.tripmate.model.Place
import com.example.tripmate.view_model.AuthViewModel
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var searchBar: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var placeAdapter: PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        searchBar = findViewById(R.id.searchBar)
        recyclerView = findViewById(R.id.rvUsers)

        recyclerView.layoutManager = LinearLayoutManager(this)
        placeAdapter = PlaceAdapter(emptyList()) { place -> openPlaceDetail(place) }
        recyclerView.adapter = placeAdapter

        val searchPlace = searchBar.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchPlace.setHintTextColor(ContextCompat.getColor(this, R.color.black))
        searchPlace.setTextColor(ContextCompat.getColor(this, R.color.black))
        searchBar.setBackgroundColor(ContextCompat.getColor(this, R.color.gray_background))

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                performSearch(newText)
                return true
            }
        })
    }

    private fun performSearch(query: String?) {
        lifecycleScope.launch {
            try {
                val places = authViewModel.searchPlaces(query)
                val sortedPlaces = places.sortedByDescending { it.rating }
                placeAdapter.submitList(sortedPlaces)
            } catch (e: Exception) {
                Toast.makeText(this@HomeActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun openPlaceDetail(place: Place) {
        val intent = Intent(this, PlaceDetailActivity::class.java).apply {
            putExtra("place_name", place.nama_wisata)
            putExtra("place_description", place.description)
            putExtra("place_image", place.gambar as? Int ?: 0)
            putExtra("place_rating", place.rating)
            putExtra("place_harga", place.harga_masuk)
        }
        startActivity(intent)
    }
}
