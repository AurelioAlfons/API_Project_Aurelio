package com.example.api_project_aurelio.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.data.ArtworkEntity

// ViewHolder class to hold references to views for each item
class ArtworkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.title)
    private val artist: TextView = view.findViewById(R.id.name)
    private val year: TextView = view.findViewById(R.id.year)
    private val medium: TextView = view.findViewById(R.id.medium)

    // Bind the data to the views
    fun bind(artwork: ArtworkEntity) {
        title.text = artwork.artworkTitle
        artist.text = artwork.artist
        year.text = artwork.year.toString()
        medium.text = artwork.medium
    }
}
