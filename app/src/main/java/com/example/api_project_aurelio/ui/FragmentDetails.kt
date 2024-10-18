package com.example.api_project_aurelio.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.api_project_aurelio.R
import dagger.hilt.android.AndroidEntryPoint

// Purpose:
// - Take data from dashboard recyclerview and list it inside material card

@AndroidEntryPoint
class FragmentDetails : Fragment() {

    private val args: FragmentDetailsArgs by navArgs()

    // Use the fragment_details xml
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    // Logic
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pass data into material card elements
        view.findViewById<TextView>(R.id.cardTitle).text = args.title
        view.findViewById<TextView>(R.id.cardName).text = args.artist
        view.findViewById<TextView>(R.id.cardMedium).text = args.medium
        view.findViewById<TextView>(R.id.cardYear).text = args.year.toString()
        view.findViewById<TextView>(R.id.cardDescription).text = args.description

        val backButton = view.findViewById<Button>(R.id.back)
        backButton.setOnClickListener {
            // Navigate back to the dashboard fragment
            findNavController().navigate(R.id.action_fragmentDetails_to_fragmentDashboard)
        }
    }
}