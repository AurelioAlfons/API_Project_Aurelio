package com.example.api_project_aurelio.ui

import android.annotation.SuppressLint
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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.api_project_aurelio.R
import com.example.api_project_aurelio.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// Purpose:
// - Handles the login UI
// - Connects with LoginViewModel
// - Handles navigation sign-in button

// Enable Hilt injection - All activity & fragment that uses DI have to have this
@AndroidEntryPoint
class FragmentLogin : Fragment() {

    // Initialized var
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorTextView: TextView

    // Use Hilt's ViewModel injection from LoginViewModel
    private val loginViewModel: LoginViewModel by viewModels()

    // Layout: fragment_login
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    // Activity
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views with the textField from the login fragment xml
        // Input is the textField that reads values
        // ErrorTextView -> Is actually a Button
        usernameEditText = view.findViewById(R.id.usernameInput)
        passwordEditText = view.findViewById(R.id.passwordInput)
        errorTextView = view.findViewById(R.id.errorButton)

        // Built in
        // Handle edge-to-edge display adjustments
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Sign in button OnClickListener
        view.findViewById<Button>(R.id.SignIn).setOnClickListener {
            // Trim() removes any unnecessary space from the value - take from the
            // declare new value from the editText with trim() string
            // Convert to String
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            //                  ^
            //                  |
            //                  |
            // VALIDATION: If else condition
            // If username or password field is empty, show error
            if (username.isEmpty() || password.isEmpty()) {
                // Change the error message and make it visible - Was Gone before in xml
                errorTextView.text = "Username and password cannot be empty"
                errorTextView.visibility = View.VISIBLE
            }
            // If not empty
            else {
                // Using Coroutine lifecycleScope -> Lives until job is completed
                // .launch -> don't need to return a result
                // Call the login function in LoginViewModel
                // Launch in background again to not block the UI
                lifecycleScope.launch {

                    // Successful condition
                    // Calls loginViewModel() function from the LoginViewModel
                    // Take username & password
                    // Wait for keypass to return onSuccess
                    loginViewModel.login(username, password, onSuccess = { keypass ->
                        // If keypass return then set the visibility of the error to GONE(Already GONE default)
                        errorTextView.visibility = View.GONE
                        // Navigate to the dashboard if login is successful
                        // function declare at line:113
                        navigateToDashboard()

                    }, onError = { errorMessage ->
                        // Error condition - All in the LoginViewModel
                        errorTextView.text = "Login failed: $errorMessage"
                        errorTextView.visibility = View.VISIBLE
                    })
                }
            }
        }
    }

    // Function to navigate to Dashboard
    private fun navigateToDashboard() {
        // Create value navOptions to handle builder (Configuration) for the function
        val navOptions = NavOptions.Builder()
            // This removes the back stack when navigating to different fragment
            .setPopUpTo(R.id.fragmentLogin, true)
            .build()

        // Move from login to dashboard - while calling the navOptions value
        findNavController().navigate(R.id.action_fragmentLogin_to_fragmentDashboard, null, navOptions)

        // Update the bottom navigation selectedId to Dashboard, so the selected navbar goes to Dashboard
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.Navigation_dash
    }
}
