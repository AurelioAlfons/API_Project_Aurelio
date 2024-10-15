package com.example.api_project_aurelio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.api_project_aurelio.R
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class FragmentLogin : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the Sign In button
        val signInButton: Button = view.findViewById(R.id.SignIn)

        // Set up click listener for the Sign In button
        signInButton.setOnClickListener {
            navigateToDashboard()
        }
    }

    private fun navigateToDashboard() {
        // Customize the navigation options if needed
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentLogin, true) // Remove Login from back stack
            .build()

        // Navigate to Dashboard
        findNavController().navigate(R.id.action_fragmentLogin_to_fragmentDashboard, null, navOptions)
    }
}
