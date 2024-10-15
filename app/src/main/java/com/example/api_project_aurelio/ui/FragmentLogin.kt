package com.example.api_project_aurelio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.api_project_aurelio.R
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        // Handle edge-to-edge display adjustments
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.fragmentLogin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Declare variable to go Dashboard Page
        val goToHome: Button = view.findViewById(R.id.SignIn)

        // On Click Listener
        goToHome.setOnClickListener {
            // Disable the button
            goToHome.isEnabled = false

            // Clear focus from the button
            goToHome.clearFocus()

            // Customize the navigation
            val navOptions = NavOptions.Builder()
                // setPopUpTo Remove LoginFragment from back stack
                // https://developer.android.com/reference/androidx/navigation/NavOptions.Builder#setPopUpTo(kotlin.Int,kotlin.Boolean,kotlin.Boolean)
                .setPopUpTo(R.id.fragmentLogin, true)
                .build()

            // Move from login to Dashboard
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentDashboard, null, navOptions)

            // Update the bottom navigation selectedId to Dashboard
            val navView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            navView?.selectedItemId = R.id.Navigation_dash
        }
    }
}
