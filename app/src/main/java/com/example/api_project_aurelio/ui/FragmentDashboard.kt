package com.example.api_project_aurelio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.adapter.ArtAdapter
import com.example.api_project_aurelio.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Purpose:
// - Dashboard fragment to display a list of artworks using a RecyclerView
// - Uses a ViewModel to fetch and observe the list of artworks
// - Binds the artwork data to the RecyclerView using ArtAdapter

@AndroidEntryPoint
class FragmentDashboard : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var artAdapter: ArtAdapter
//    private lateinit var emptyDashboardButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        artAdapter = ArtAdapter()
        recyclerView.adapter = artAdapter

        // Collect the artworkEntities flow and update RecyclerView
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dashboardViewModel.artworkEntities.collect { artworks ->
                    artAdapter.submitList(artworks)
                }
            }
        }

        // Fetch the artworks when the fragment is created
        dashboardViewModel.fetchArtworks()
    }
}
