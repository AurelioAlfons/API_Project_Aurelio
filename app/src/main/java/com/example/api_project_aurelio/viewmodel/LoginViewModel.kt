package com.example.api_project_aurelio.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_project_aurelio.data.LoginRequest
import com.example.api_project_aurelio.network.RestfulApiDevService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Purpose:
// - Handles logic & communicate with API
// - Handles the login logic (try, catch (e: Exception)
// - Connect between the UI (FragmentLogin & API call)
// - Get the API service via Hilt Dependency Injection
// - Error handling
// - Also has Coroutines

// Let Hilt know that this file will be Injected with dependencies
// HiltViewModel added to viewModels in project
@HiltViewModel

// CLASS: LoginViewModel - DEPENDENCY INJECTION
// Inject will request dependencies and automatically provide the tools when class is created
class LoginViewModel @Inject constructor(

    // DEPENDENCY INJECTIONS:

    // - apiService: Injected for API calls <-- NetworkModule
    private val apiService: RestfulApiDevService,

    // - appContext: Injected for accessing shared preferences <-- Built in
    @ApplicationContext private val appContext: Context

) : ViewModel() {

    // Function to handle login
    // - Username & Password
    // - onSuccess -> Indicates the condition for login successful
    // - Unit -> Return type but not return a value
    fun login(username: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {

        // Launch coroutine in viewModelScope to launch activities in background without blocking the UI
        // When clicking the Sign In button it handles the network request and wait for response
        // Once the ViewModel is cleared it will cancel the Coroutines
        viewModelScope.launch {
            // Used for handling errors and catch errors
            try {
                // Use API service to call login send username & password
                val loginResponse = apiService.loginService(LoginRequest(username, password))
                // Return a response
                val keypass = loginResponse.keypass

                // Save keypass in SharedPreferences -> Small bits of data in internal storage
                // APP_PREFS -> storage for data
                // Context.MODE_PRIVATE -> Ensures only this app can access
                val sharedPreferences = appContext.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("keypass", keypass).apply()

                // Check on logCat
                Log.d("LoginViewModel", "Received keypass: ${loginResponse.keypass}")

                // If successful, pass the keypass to the onSuccess callback
                onSuccess(loginResponse.keypass)

            } // Error handling
            catch (e: HttpException) {
                // Handle HTTP exceptions
                Log.e("LoginViewModel", "Login failed: ${e.message}")
                onError("Login failed: ${e.message}")

            } catch (e: IOException) {
                // Handle network or conversion issues
                onError("Network error: ${e.message}")

            } catch (e: Exception) {
                // Handle any other exception
                onError("An error occurred: ${e.message}")
            }
        }
    }
}
