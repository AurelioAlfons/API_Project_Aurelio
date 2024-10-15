// RestfulApiDevService.kt
package com.example.api_project_aurelio.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RestfulApiDevService {

    // This is for login
    @POST("footscray/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    // This is to get the art data
    @GET("dashboard/art")
    suspend fun getArt(
        @Header("Authorization") keypass: String
    ): ApiResponse
}

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String // Modify this based on your actual response structure
)

// Artwork data response classes
data class ArtworkEntity(
    val artworkTitle: String,
    val artist: String,
    val medium: String,
    val year: Int,
    val description: String
)

data class ApiResponse(
    val entities: List<ArtworkEntity>,
    val entityTotal: Int
)


