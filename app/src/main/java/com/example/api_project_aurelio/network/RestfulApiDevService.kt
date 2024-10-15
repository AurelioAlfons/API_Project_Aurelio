package com.example.api_project_aurelio.network

import retrofit2.http.Body
import retrofit2.http.POST

// Define the API service interface
interface RestfulApiDevService {

    // Define the login endpoint
    @POST("footscray/auth") // Adjust this path based on your API structure
    suspend fun login(@Body credentials: LoginCredentials): LoginResponse
}

// Data class for login credentials
data class LoginCredentials(
    val username: String,
    val password: String
)

// Data class for the expected login response
data class LoginResponse(
    val keypass: String
)
