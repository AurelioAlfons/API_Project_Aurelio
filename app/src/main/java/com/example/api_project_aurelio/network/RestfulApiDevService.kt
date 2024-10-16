// RestfulApiDevService.kt
package com.example.api_project_aurelio.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// Purpose:
// - Contains the 2 API Endpoints: @POST("footscray/auth") & @GET("dashboard/art")
// - Handles login and data fetching
// - Converts kotlin data class into JSON

interface RestfulApiDevService {

    // POST
    // This is for login
    @POST("footscray/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    // GET
    // This is to get the art data
    @GET("dashboard/art")
    suspend fun getArt(
        @Header("Authorization") keypass: String
    ): ApiResponse
    // Includes the keypass, and it returns ApiResponse
}

// Data sent to login request
data class LoginRequest(
    val username: String,
    val password: String
)

// Data receive from login
data class LoginResponse(
    val keypass: String
)

// Entities list receive from the API keypass
data class ArtworkEntity(
    val artworkTitle: String,
    val artist: String,
    val medium: String,
    val year: Int,
    val description: String
)

// API response of how manny items in the list
data class ApiResponse(
    val entities: List<ArtworkEntity>,
    val entityTotal: Int
)


