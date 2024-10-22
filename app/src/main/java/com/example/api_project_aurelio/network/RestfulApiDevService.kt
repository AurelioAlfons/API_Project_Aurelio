// RestfulApiDevService.kt
package com.example.api_project_aurelio.network

import com.example.api_project_aurelio.data.ApiResponse
import com.example.api_project_aurelio.data.LoginRequest
import com.example.api_project_aurelio.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// Purpose:
// - Contains the 2 API Endpoints: @POST("footscray/auth") & @GET("dashboard/art")

// Includes methods of fetching and posting data to the API
// Its like using POSTMAN to test the API
interface RestfulApiDevService {

    // POST
    // This is for login
    @POST("footscray/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    // GET
    // This is to get the art data
    @GET("dashboard/art")
    suspend fun getArt(
        // Header is used to send extra information (token)
        @Header("Authorization") keypass: String
    ): ApiResponse
    // Includes the keypass, and it returns ApiResponse -> Data folder
}


