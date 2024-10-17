package com.example.api_project_aurelio.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.api_project_aurelio.R

class FragmentDetails : Fragment() {

    private val args: FragmentDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the argument data to the TextView
        view.findViewById<TextView>(R.id.passedData).text = """
            Title: ${args.title}
            Artist: ${args.artist}
            Medium: ${args.medium}
            Year: ${args.year}
            Description: ${args.description}
        """.trimIndent()
    }
}