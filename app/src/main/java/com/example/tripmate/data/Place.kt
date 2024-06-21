package com.example.tripmate.model

data class Place(
    val id: String,
    val nama_wisata: String,
    val kota: String,
    val harga_masuk: String,
    val description: String,
    val gambar: Int,
    val rating: Double
)
