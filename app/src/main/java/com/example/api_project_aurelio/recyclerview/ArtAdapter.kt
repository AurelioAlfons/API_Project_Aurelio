package com.example.api_project_aurelio.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.data.ArtworkEntity
import com.example.api_project_aurelio.recyclerview.ArtworkViewHolder

// Purpose:
// - Adapter to handle the display of artwork data in a RecyclerView
// - Responsible for creating ViewHolder instances and binding data to the RecyclerView

// Adapter class to manage the artwork data
class ArtAdapter(private var artworkList: List<ArtworkEntity> = emptyList(),
                 private val navigationFunction: (ArtworkEntity) -> Unit) :
    RecyclerView.Adapter<ArtworkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_restful_api_dev, parent, false)
        return ArtworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        // Fetch the art item index
        val artwork = artworkList[position]
        // Call bind to set the item data
        holder.bind(artwork)

        // Find the button in the item layout and set a click listener
        holder.itemView.findViewById<Button>(R.id.goToDetails).setOnClickListener {
            navigationFunction(artwork) // Pass the clicked artwork to the lambda
        }
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
