package com.example.api_project_aurelio.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.network.ArtworkEntity

class ArtAdapter : RecyclerView.Adapter<ArtAdapter.ArtworkViewHolder>() {

    private var artworkList: List<ArtworkEntity> = emptyList()

    // ViewHolder to bind the layout views
    class ArtworkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val artist: TextView = view.findViewById(R.id.name)
        val year: TextView = view.findViewById(R.id.year)
        val medium: TextView = view.findViewById(R.id.medium)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_restful_api_dev, parent, false)
        return ArtworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        val artwork = artworkList[position]
        holder.title.text = artwork.artworkTitle
        holder.artist.text = artwork.artist
        holder.year.text = artwork.year.toString()
        holder.medium.text = artwork.medium
    }

    override fun getItemCount(): Int {
        return artworkList.size
    }

    // Function to update the list of artworks
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(artworks: List<ArtworkEntity>) {
        this.artworkList = artworks
        // Notify the RecyclerView to rebind all the views
        notifyDataSetChanged()
    }
}
