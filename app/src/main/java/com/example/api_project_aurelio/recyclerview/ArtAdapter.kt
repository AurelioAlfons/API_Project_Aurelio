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

// Implementations:
// - RecyclerView xml layout (fragment_dashboard)
// - xml layout for ViewHolder (item_layout_restful_api_service xml)
// - Create Adapter
// - Create ViewHolder class
// - ViewModel -> to manage data flow
// - Set data in fragment

// Adapter class to manage the artwork data
class ArtAdapter(
    // It was an empty list before
    private var artworkList: List<ArtworkEntity> = emptyList(),
    // So this will navigate to Details with the selected list data
    // Unit doesn't return anything like void
    private val navigationFunction: (ArtworkEntity) -> Unit) :

    RecyclerView.Adapter<ArtworkViewHolder>() {

        // Creates new VideHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        // Using the item_layout_restful_api_service xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_restful_api_dev, parent, false)
        return ArtworkViewHolder(view)
    }

    // Binds the data to ViewHolder
    // We use the holder bind inside the ArtWorkViewHolder
    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        // Fetch the art item index
        val artwork = artworkList[position]
        // Call bind to set the item data
        holder.bind(artwork)

        // Find the button in the item layout and set a click listener
        holder.itemView.findViewById<Button>(R.id.goToDetails).setOnClickListener {
            // Pass the clicked artwork to the lambda
            navigationFunction(artwork)
        }
    }

    // Returns a total number of the items
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
