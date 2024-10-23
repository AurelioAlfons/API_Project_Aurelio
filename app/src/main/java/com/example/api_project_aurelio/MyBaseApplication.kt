package com.example.api_project_aurelio

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

// Base application for Hilt
// - Initialized Hilt dependencies
// - @HiltAndroidApp -> Entry point for dependency injection
// - Make dependencies available global like a global class
// - Does not receive DI but declaring the app will use DI

// Implementation:
// - @HiltAndroidApp => sets up DI globally.
// - @AndroidEntryPoint => injects dependencies into activities/fragments.
// - @HiltViewModel => injects dependencies into ViewModels.

// Application level dependency container
@HiltAndroidApp
class MyBaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Make sure it runs in LogCat for detecting issues
        Log.d("nit3213", "Application onCreate was called")
    }
}