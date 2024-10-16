package com.example.api_project_aurelio.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_project_aurelio.di.NetworkModule
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Purpose:
// - Handles logic & communicate with API
// - Handles the login logic (try, catch (e: Exception)
// - Connect between the UI (FragmentLogin & API call)
// - Get the API service from NetworkModule CLASS (Sets up Retrofit and DevService)
// - Error handling
// - Also has Coroutines

// CLASS: LoginViewModel
class LoginViewModel(private val networkModule: NetworkModule) : ViewModel() {

    // Function to handle login
    // - Username & Password
    // - onSuccess -> Indicates the condition for login successful
    // - Unit -> Return type but not return a value
    fun login(username: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        //
        // Launch coroutine in viewModelScope to handle background task
        // Coroutines so UI thread won't be blocked while waiting for network response
        viewModelScope.launch {
            try {
                // Use NetworkModel to call login
                val loginResponse = networkModule.login(username, password)

                Log.d("LoginViewModel", "Received keypass: ${loginResponse.keypass}")

                // If successful, pass the keypass to the onSuccess callback
                onSuccess(loginResponse.keypass)

            } catch (e: HttpException) {
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
