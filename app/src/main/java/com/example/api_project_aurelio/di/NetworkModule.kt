package com.example.api_project_aurelio.di

//import com.example.api_project_aurelio.network.LoginRequest
//import com.example.api_project_aurelio.network.LoginResponse
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

// CLASS: NetworkModule
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): RestfulApiDevService {
        // Provide RestfulApiDevService automatically via Hilt
        return RestfulApiDevRetrofitClient.apiService
    }
}
