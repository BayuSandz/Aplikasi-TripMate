package com.example.tripmate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tripmate.databinding.ItemPlaceBinding
import com.example.tripmate.model.Place

class PlaceAdapter(
    private var places: List<Place>,
    private val clickListener: (Place) -> Unit
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    inner class PlaceViewHolder(private val binding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.placeName.text = place.nama_wisata
            binding.placeCity.text = place.kota
            binding.placeRating.text = String.format("%.1f", place.rating)
            binding.root.setOnClickListener { clickListener(place) }

            val context = binding.placeImage.context
            if (place.gambar is Int) {
                binding.placeImage.setImageDrawable(ContextCompat.getDrawable(context, place.gambar))
            } else {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(places[position])
    }

    override fun getItemCount(): Int = places.size

    fun submitList(newPlaces: List<Place>) {
        places = newPlaces
        notifyDataSetChanged()
    }
}
