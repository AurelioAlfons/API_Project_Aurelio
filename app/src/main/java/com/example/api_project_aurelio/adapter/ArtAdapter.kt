//package com.example.api_project_aurelio.adapter
//
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.api_project_aurelio.R
//import com.example.api_project_aurelio.network.ArtworkEntity
//
//class ArtAdapter(private val artworkList: List<ArtworkEntity>) : RecyclerView.Adapter<ArtAdapter.ArtworkViewHolder>() {
//
//    class ArtworkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val titleTextView: TextView = view.findViewById(R.id.artworkTitle)
//        val artistTextView: TextView = view.findViewById(R.id.artist)
//        // Add other views for medium, year, description if needed
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_artwork, parent, false)  // Inflate your item layout here
//        return ArtworkViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
//        val artwork = artworkList[position]
//        holder.titleTextView.text = artwork.artworkTitle
//        holder.artistTextView.text = artwork.artist
//        // Bind other views as needed
//    }
//
//    override fun getItemCount() = artworkList.size
//}
