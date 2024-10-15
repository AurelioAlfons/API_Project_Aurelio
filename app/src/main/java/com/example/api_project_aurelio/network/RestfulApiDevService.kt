// RestfulApiDevService.kt
package com.example.api_project_aurelio.network

import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String // Modify this based on your actual response structure
)

interface RestfulApiDevService {
    @POST("footscray/auth")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse
}
