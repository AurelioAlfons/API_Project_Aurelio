package com.example.api_project_aurelio.di

import com.example.api_project_aurelio.network.LoginRequest
import com.example.api_project_aurelio.network.LoginResponse
import com.example.api_project_aurelio.network.RestfulApiDevService

class NetworkModule(private val apiService: RestfulApiDevService) {

    // Function to call the login API
    suspend fun login(username: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(username, password)
        return apiService.login(loginRequest)
    }
}
