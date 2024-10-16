package com.example.api_project_aurelio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.api_project_aurelio.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetails : Fragment() {

    private var artworkTitle: String? = null
    private var artist: String? = null
    private var medium: String? = null
    private var year: Int? = null
    private var description: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the arguments passed from the previous fragment
//        arguments?.let {
//            val args = FragmentDetailsArgs.fromBundle(it)
//            artworkTitle = args.title
//            artist = args.artist
//            medium = args.medium
//            year = args.year
//            description = args.description
//        }
//        arguments?.let { bundle ->
//            artworkTitle = bundle.getString("title")
//            artist = bundle.getString("artist")
//            medium = bundle.getString("medium")
//            year = bundle.getInt("year") // Retrieve the integer
//            description = bundle.getString("description")
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set data to the MaterialCardView elements
//        view.findViewById<TextView>(R.id.cardTitle).text = artworkTitle
//        view.findViewById<TextView>(R.id.cardName).text = artist
//        view.findViewById<TextView>(R.id.cardMedium).text = medium
//        view.findViewById<TextView>(R.id.cardYear).text = year.toString()
//        view.findViewById<TextView>(R.id.cardDescription).text = description
    }
}
