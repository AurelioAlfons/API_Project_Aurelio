package com.example.api_project_aurelio.recyclerview

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.data.ArtworkEntity

// Purpose:
// - Holds references to the views for each RecyclerView item
// - Binds artwork data to these views

// ViewHolder class to hold references to views for each item
class ArtworkViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Right: is from the item_layout_restful_api_dev
    // Left: is a declared values
    private val title: TextView = view.findViewById(R.id.title)
    private val artist: TextView = view.findViewById(R.id.name)
    private val year: TextView = view.findViewById(R.id.year)
    private val medium: TextView = view.findViewById(R.id.medium)

    // Bind the data to the views
    @SuppressLint("SetTextI18n")

    // Right: is from the ArtworkEntity Class
    // Left: if from initialize item up there
    fun bind(artwork: ArtworkEntity) {
        title.text = artwork.artworkTitle
        artist.text = artwork.artist
        year.text = "Year: ${artwork.year}"
        medium.text = artwork.medium
    }
}
