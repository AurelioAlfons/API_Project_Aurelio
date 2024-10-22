package com.example.api_project_aurelio.di

//import com.example.api_project_aurelio.data.LoginRequest
//import com.example.api_project_aurelio.data.LoginResponse
import com.example.api_project_aurelio.network.RestfulApiDevRetrofitClient
import com.example.api_project_aurelio.network.RestfulApiDevService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Purpose:
// - Handles API calls
// - Function to call the login API taking username and password
// - Calling the RestfulApiDevService Interface to make network request
// - Connect ViewModel and API service

// DI used in:
// - NetworkModule
// - LoginViewModel & DashboardViewModel

// Marks as a Dagger Module and it contains methods that provide dependencies
@Module

// Installin is used to specify which HILT component module should be installed in
// Make the dependency available throughout the app
@InstallIn(SingletonComponent::class)

// OBJECT: NetworkModule
object NetworkModule {
    // Inform hilt, this function will add Dependency
    @Provides
    // Only one API is shared and used
    @Singleton
    // function to call for API
    fun provideApiService(): RestfulApiDevService {
        // Provide RestfulApiDevService automatically via Hilt
        return RestfulApiDevRetrofitClient.apiService
    }
}
