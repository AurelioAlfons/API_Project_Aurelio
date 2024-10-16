package com.example.api_project_aurelio.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.data.ArtworkEntity
import com.example.api_project_aurelio.recyclerview.ArtworkViewHolder

// Adapter class to manage the artwork data
class ArtAdapter(private var artworkList: List<ArtworkEntity> = emptyList()) :
    RecyclerView.Adapter<ArtworkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_restful_api_dev, parent, false)
        return ArtworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        val artwork = artworkList[position]
        holder.bind(artwork) // Call bind to set the item data
    }

    override fun getItemCount(): Int {
        return artworkList.size
    }

    // Update the list of artwork entities and notify the adapter
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(artworks: List<ArtworkEntity>) {
        artworkList = artworks
        notifyDataSetChanged()
    }
}