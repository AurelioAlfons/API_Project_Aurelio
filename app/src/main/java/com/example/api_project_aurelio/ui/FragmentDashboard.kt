package com.example.api_project_aurelio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDashboard : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyDashboardButton: Button
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerView)
        emptyDashboardButton = view.findViewById(R.id.emptyDashboard)

        // Observe the artworkEntities LiveData
        dashboardViewModel.artworkEntities.observe(viewLifecycleOwner, Observer { entities ->
            if (entities.isNullOrEmpty()) {
                // Show the empty button if no data
                emptyDashboardButton.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                // Show RecyclerView if data exists
                emptyDashboardButton.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })

        // Fetch data
        dashboardViewModel.fetchArtworks()
    }
}
