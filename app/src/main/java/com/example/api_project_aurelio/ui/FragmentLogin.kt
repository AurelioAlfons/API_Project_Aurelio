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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.di.NetworkModule
import com.example.api_project_aurelio.model_factory.LoginViewModelFactory
import com.example.api_project_aurelio.viewmodel.LoginViewModel
import com.example.api_project_aurelio.network.RestfulApiDevRetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class FragmentLogin : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorTextView: TextView

    private lateinit var loginViewModel: LoginViewModel

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

        // Initialize the ViewModel with the NetworkModule
        val networkModule = NetworkModule(RestfulApiDevRetrofitClient.apiService)
        val factory = LoginViewModelFactory(networkModule)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        // Sign in button OnClickListener
        view.findViewById<Button>(R.id.SignIn).setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // If username or password field is empty, show error
            if (username.isEmpty() || password.isEmpty()) {
                errorTextView.text = "Username and password cannot be empty"
                errorTextView.visibility = View.VISIBLE
            } else {
                // Call the login function in ViewModel
                lifecycleScope.launch {
                    loginViewModel.login(username, password, onSuccess = { keypass ->
                        errorTextView.visibility = View.GONE
                        navigateToDashboard()  // Navigate to the dashboard if login is successful
                    }, onError = { errorMessage ->
                        errorTextView.text = "Login failed: $errorMessage"
                        errorTextView.visibility = View.VISIBLE
                    })
                }
            }
        }
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
