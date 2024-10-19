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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.adapter.ArtAdapter
import com.example.api_project_aurelio.data.ArtworkEntity
import com.example.api_project_aurelio.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Purpose:
// - Dashboard fragment to display a list of artworks using a RecyclerView
// - Uses a ViewModel to fetch and observe the list of artworks
// - Binds the artwork data to the RecyclerView using ArtAdapter

// Enable Hilt injection
@AndroidEntryPoint
class FragmentDashboard : Fragment() {

    // Initialized Dependency Injections
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var artAdapter: ArtAdapter

    // Use the fragment_dashboard xml
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    // All the logic happen here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Define the navigation function lambda using safeArgs
        // Lambda is like passing a parameter in Java
        val navigationFunctionLambda: (ArtworkEntity) -> Unit = { artwork ->

            // Use safeArgs to navigate to details fragment, passing the artwork data
            val action = FragmentDashboardDirections.actionFragmentDashboardToFragmentDetails(
                title = artwork.artworkTitle,
                artist = artwork.artist,
                medium = artwork.medium,
                year = artwork.year,
                description = artwork.description
            )

            // Navigate to action (Details)
            findNavController().navigate(action)
        }

        // Initialize recyclerViews from the fragment_dashboard ID
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // initialized artAdapter and pass function navigate lambda when clicked
        artAdapter = ArtAdapter(navigationFunction = navigationFunctionLambda)

        // Connect ArtAdapter to RecyclerView to displays data
        recyclerView.adapter = artAdapter

        // Collect the artworkEntities flow and update RecyclerView
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                dashboardViewModel.artworkEntities.collect { artworks ->
                    artAdapter.submitList(artworks)
                    // submitList -> in  ArtAdapter
                }
            }
        }

        // Fetch the artworks when the fragment is created
        dashboardViewModel.fetchArtworks()
    }
}

