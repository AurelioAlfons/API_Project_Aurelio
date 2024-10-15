package com.example.api_project_aurelio.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.network.LoginRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class FragmentLogin : Fragment() {

    // Value of the var is still empty
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        usernameEditText = view.findViewById(R.id.usernameInput)
        passwordEditText = view.findViewById(R.id.passwordInput)
        errorTextView = view.findViewById(R.id.errorButton)

        // Handle edge-to-edge display adjustments
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        // Login Validation Starts here (With Post method)
//        // Sign in button OnClickListener
//        view.findViewById<Button>(R.id.SignIn).setOnClickListener {
//            // Trim = remove spaces
//            val username = usernameEditText.text.toString().trim()
//            val password = passwordEditText.text.toString().trim()
//
//            // If username or password field empty then make error message visible
//            if (username.isEmpty() || password.isEmpty()) {
//                errorTextView.text = "Username and password cannot be empty"
//                errorTextView.visibility = View.VISIBLE
//            } else {
//                // Call the login API
//                lifecycleScope.launch {
//                    try {
//                        // Create login request object
//                        val loginRequest = LoginRequest(username = username, password = password)
//
//                        // Call login API using Retrofit service
//                        val loginResponse = ApiClient.restfulApiDevService.login(loginRequest)
//
//                        // If login is successful (assuming you check for keypass or a success flag in the response)
//                        if (loginResponse.keypass.isNotEmpty()) {
//                            errorTextView.visibility = View.GONE
//                            // Save keypass if needed and navigate to dashboard
//                            navigateToDashboard()  // Navigate to dashboard
//                        } else {
//                            // Show error message for incorrect credentials
//                            errorTextView.text = "Incorrect username or password"
//                            errorTextView.visibility = View.VISIBLE
//                        }
//                    } catch (e: Exception) {
//                        // Handle network or API errors (e.g., connection failure)
//                        errorTextView.text = "Login failed: ${e.message}"
//                        errorTextView.visibility = View.VISIBLE
//                    }
//                }
//            }
//        }
        // Login Validation ends here (POST)

        //
        //Sign in button OnClickListener
        // Login Validation Starts here (Without Post Method),(Declaring the username and password inside)
        view.findViewById<Button>(R.id.SignIn).setOnClickListener {
            // Trim = remove spaces
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // If username or password Correct
//            if (username == "Aurelio" || password == "s4672291") {
//                errorTextView.visibility = View.GONE
//                navigateToDashboard()
            // Just for testing make it shorter
            if (username == "a" || password == "a") {
                errorTextView.visibility = View.GONE
                navigateToDashboard()
            }
            // If username or password field empty then make error message visible
            else if (username.isEmpty() || password.isEmpty()) {
                errorTextView.text = "Username and password cannot be empty"
                errorTextView.visibility = View.VISIBLE
            }
            // If username or password field wrong
            else {
                errorTextView.text = "Incorrect username or password"
                errorTextView.visibility = View.VISIBLE
            }
        }
        // Login Validation Ends here (No POST)
        //
    }

    // Function to navigate to Dashboard
    private fun navigateToDashboard() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.fragmentLogin, true)
            .build()

        // Move from login to dashboard
        findNavController().navigate(R.id.action_fragmentLogin_to_fragmentDashboard, null, navOptions)

        // Update the bottom navigation selectedId to Dashboard
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.Navigation_dash
    }
}
